package com.chinnsenn.widget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	private val mEdtAutoResizing by lazy(LazyThreadSafetyMode.NONE) { findViewById<AutoResizingEditText>(R.id.edt_auto_resizing) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		mEdtAutoResizing.setResizingConfig(maxSize = 13.spF(applicationContext), minSize = 2.spF(applicationContext), threshold = 5, step = 2.spF(applicationContext))

	}
}