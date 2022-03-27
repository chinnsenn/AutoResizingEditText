package com.chinnsenn.widget

import android.content.Context
import android.util.TypedValue

/**
 * @author: 陈川
 * @date: 2022/3/27
 */

fun Int.dp(context: Context): Int {
	return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_DIP,
		this.toFloat(),
		context.resources.displayMetrics
	).toInt()
}

fun Int.dpF(context: Context): Float {
	return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_DIP,
		this.toFloat(),
		context.resources.displayMetrics
	)
}

fun Int.sp(context: Context): Int {
	return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_SP,
		this.toFloat(),
		context.resources.displayMetrics
	).toInt()
}

fun Int.spF(context: Context): Float {
	return TypedValue.applyDimension(
		TypedValue.COMPLEX_UNIT_SP,
		this.toFloat(),
		context.resources.displayMetrics
	)
}