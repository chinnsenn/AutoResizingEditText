package com.chinnsenn.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import java.lang.ref.WeakReference

/**
 * @author: 陈川
 * @date: 2022/3/27
 */
class AutoResizingTextWatcher(context: Context) : TextWatcher {

	private var weakReference = WeakReference(context)

	private var mCallBack: AutoResizingTextWatcherCallback? = null

	private var minTextSize: Float = 0f

	private var maxTextSize: Float = 0f

	private var resizingThreshold: Int = 5

	private var stepByStep: Float = 0f

	init {
		weakReference.get()?.also {
			minTextSize = 12f
			maxTextSize = 20f
			stepByStep = 1f
		}
	}

	fun setAutoResizingTextWatcherCallback(callback: AutoResizingTextWatcherCallback) {
		mCallBack = callback
	}

	fun setMinTextSize(minTextSize: Float) {
		this.minTextSize = minTextSize
	}

	fun setMaxTextSize(maxTextSize: Float) {
		this.maxTextSize = maxTextSize
	}

	fun setResizingThreshold(resizingThreshold: Int) {
		this.resizingThreshold = resizingThreshold
	}

	fun setStep(stepByStep: Float) {
		this.stepByStep = stepByStep
	}

	override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

	}

	override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
	}

	override fun afterTextChanged(s: Editable?) {
		resizingText(s)
	}

	fun resizingText(s: Editable?) {
		s?.also { text ->
			val length = text.length
			if (length <= resizingThreshold) {
				mCallBack?.onResizing(maxTextSize)
			} else {
				val gap = (length - resizingThreshold) * stepByStep
				val currentSize = (maxTextSize - gap).coerceAtLeast(minTextSize)
				mCallBack?.onResizing(currentSize)
			}
		}
	}

	interface AutoResizingTextWatcherCallback {
		fun onResizing(textSize: Float)
	}
}
