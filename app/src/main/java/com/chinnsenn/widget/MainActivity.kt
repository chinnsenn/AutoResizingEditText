package com.chinnsenn.widget

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
	private val mEdtAutoResizing by lazy(LazyThreadSafetyMode.NONE) { findViewById<AutoResizingEditText>(R.id.edt_auto_resizing) }
	private val mEdtMaxSize by lazy(LazyThreadSafetyMode.NONE) { findViewById<AppCompatEditText>(R.id.edt_max_size) }
	private val mEdtMinSize by lazy(LazyThreadSafetyMode.NONE) { findViewById<AppCompatEditText>(R.id.edt_min_size) }
	private val mEdtThreshold by lazy(LazyThreadSafetyMode.NONE) { findViewById<AppCompatEditText>(R.id.edt_threshold) }
	private val mEdtStep by lazy(LazyThreadSafetyMode.NONE) { findViewById<AppCompatEditText>(R.id.edt_step) }
	private val mBtnSubmit by lazy(LazyThreadSafetyMode.NONE) { findViewById<AppCompatButton>(R.id.btn_submit) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		mBtnSubmit.setOnClickListener {
			val maxSize = mEdtMaxSize.text.toString().toFloatOrNull()
			if (maxSize == null) {
				Toast.makeText(this, "请输入整型数字", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}
			val minSize = mEdtMinSize.text.toString().toFloatOrNull()
			if (minSize == null) {
				Toast.makeText(this, "请输入整型数字", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}
			val threshold = mEdtThreshold.text.toString().toIntOrNull()
			if (threshold == null) {
				Toast.makeText(this, "请输入整型数字", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}
			val step = mEdtStep.text.toString().toFloatOrNull()
			if (step == null) {
				Toast.makeText(this, "请输入整型数字", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			mEdtAutoResizing.setResizingConfig(maxSize, minSize, threshold, step)
		}


	}
}