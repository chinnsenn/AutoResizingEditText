package com.chinnsenn.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
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
			minTextSize = ta.getDimension(R.styleable.AutoResizingEditText_resizing_min_size, 11f)
			maxTextSize = ta.getDimension(R.styleable.AutoResizingEditText_resizing_max_size, 20f)
			resizingThreshold = ta.getInt(R.styleable.AutoResizingEditText_resizing_threshold, 10)
			stepByStep = ta.getDimension(R.styleable.AutoResizingEditText_resizing_step, 1f)
			ta.recycle()
			if (minTextSize > maxTextSize) {
				throw IllegalArgumentException("minSize 不能大于 maxSize")
			}
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
				watcher.resizingText(text)
			}
		}
	}

	@JvmOverloads
	fun setResizingConfig(
		@FloatRange(from = 0.0, fromInclusive = false, toInclusive = false) maxSize: Float,
		@FloatRange(from = 0.0, fromInclusive = false, toInclusive = false) minSize: Float,
		@IntRange(from = 1, to = Long.MAX_VALUE) threshold: Int? = null,
		@FloatRange(from = 0.0, to = 1000.0, fromInclusive = false, toInclusive = false) step: Float? = null
	) {
		if (minSize > maxSize) {
			Toast.makeText(context, "minSize 不能大于 maxSize", Toast.LENGTH_SHORT).show()
			return
		}
		this.maxTextSize = maxSize
		this.minTextSize = minSize
		mTextWatcher?.also { watcher ->
			watcher.setMaxTextSize(maxTextSize)
			watcher.setMinTextSize(minTextSize)
			threshold?.let {
				resizingThreshold = it
				watcher.setResizingThreshold(it)
			}
			step?.let {
				stepByStep = it
				watcher.setStep(it)
			}
			watcher.resizingText(text)
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