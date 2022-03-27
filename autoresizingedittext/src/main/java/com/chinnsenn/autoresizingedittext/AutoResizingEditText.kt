package com.chinnsenn.autoresizingedittext

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import java.lang.ref.WeakReference

/**
 * @author: 陈川
 * @date: 2022/3/27
 */

class AutoResizingEditText @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

	private var mTextWatcher: AutoResizingTextWatcher? = null

	private var mTextWatcherCallback: AutoResizingTextWatcherCallback? = null

	private var minTextSize: Float = 0f

	private var maxTextSize: Float = 0f

	private var resizingThreshold: Int = 5

	private var stepByStep: Float = 0f

	init {
		attrs?.also {
			val ta = context.obtainStyledAttributes(it, R.styleable.AutoResizingEditText)
			minTextSize = ta.getDimension(R.styleable.AutoResizingEditText_resizing_min_size, 11.spF(context))
			maxTextSize = ta.getDimension(R.styleable.AutoResizingEditText_resizing_max_size, 20.spF(context))
			resizingThreshold = ta.getInt(R.styleable.AutoResizingEditText_resizing_threshold, 10)
			stepByStep = ta.getDimension(R.styleable.AutoResizingEditText_resizing_step, 1f)
			ta.recycle()
			if (mTextWatcher == null) {
				mTextWatcher = AutoResizingTextWatcher(context)
			}
			if (mTextWatcherCallback == null) {
				mTextWatcherCallback = AutoResizingTextWatcherCallback(this)
				mTextWatcher?.setAutoResizingTextWatcherCallback(mTextWatcherCallback!!)
			}
			mTextWatcher?.also { watcher ->
				watcher.setMinTextSize(minTextSize)
				watcher.setMaxTextSize(maxTextSize)
				watcher.setStep(stepByStep)
				watcher.setResizingThreshold(resizingThreshold)
				watcher.afterTextChanged(text)
			}
		}
	}

	override fun onAttachedToWindow() {
		super.onAttachedToWindow()
		addTextChangedListener(mTextWatcher)
	}

	override fun onDetachedFromWindow() {
		super.onDetachedFromWindow()
		mTextWatcher?.also {
			removeTextChangedListener(it)
			mTextWatcher = null
		}
	}

	class AutoResizingTextWatcherCallback(editText: EditText) : AutoResizingTextWatcher.AutoResizingTextWatcherCallback {

		private var weakReference = WeakReference(editText)

		override fun onResizing(textSize: Float) {
			weakReference.get()?.also {
				it.textSize = textSize
			}
		}

	}
}