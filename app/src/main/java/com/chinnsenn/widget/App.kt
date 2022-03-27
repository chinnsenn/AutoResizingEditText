package com.chinnsenn.widget

import android.app.Application
import android.os.Build
import org.lsposed.hiddenapibypass.HiddenApiBypass
import timber.log.Timber

/**
 * @author: 陈川
 * @date: 2022/3/27
 */
class App : Application() {
	override fun onCreate() {
		super.onCreate()
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			HiddenApiBypass.addHiddenApiExemptions("")
		}

		Timber.plant(Timber.DebugTree())
	}
}