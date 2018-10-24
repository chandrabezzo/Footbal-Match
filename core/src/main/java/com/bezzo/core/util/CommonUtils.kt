package com.bezzo.core.util

import android.annotation.*
import android.app.*
import android.content.*
import android.content.res.*
import android.graphics.*
import android.graphics.drawable.*
import android.icu.text.SimpleDateFormat
import android.net.*
import android.os.*
import android.provider.*
import android.support.design.widget.*
import android.text.*
import android.util.*
import android.view.*
import android.widget.*

import com.bezzo.core.R
import com.bezzo.core.util.constanta.*
import com.google.android.gms.common.*

import org.json.*

import java.io.*
import java.text.NumberFormat
import java.util.*
import java.util.regex.*

/**
 * Created by bezzo on 26/09/17.
 */

object CommonUtils {

    private val TAG = "CommonUtils"

    val timeStamp: String
        @TargetApi(Build.VERSION_CODES.N)
        get() = SimpleDateFormat(AppConstans.TIMESTAMP_FORMAT, Locale.getDefault())
                .format(Date())

    val localeID: Locale
        get() = Locale("in", "ID")

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings
                .Secure.ANDROID_ID)
    }

    fun isEmailValid(email: String): Boolean {
        val pattern: Pattern
        val matcher: Matcher

        val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        pattern = Pattern.compile(EMAIL_PATTERN)
        matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {

        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, Charsets.UTF_8)
    }

    fun isJSONValid(test: String?): Boolean {

        if (test == null || test.isEmpty()) {
            return false
        }

        try {
            JSONObject(test)
        } catch (ex: JSONException) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }

        }

        return true
    }

    fun autoHideFab(fabView: FloatingActionButton, dy: Int) {
        if (dy > 0 && fabView.visibility == View.VISIBLE) {
            fabView.hide()
        } else if (dy < 0 && fabView.visibility != View.VISIBLE) {
            fabView.show()
        }
    }

    fun getPriceFormat(locale: Locale, price: Double): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(locale)

        return currencyFormat.format(price)
    }

    fun getSplittedString(text: String, regex: String): Array<String> {
        return text.split(regex.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }

    fun getColor(context: Context, id: Int): Int {
        return if (Build.VERSION.SDK_INT >= 23) {
            context.resources.getColor(id, context.theme)
        } else context.resources.getColor(id)

    }

    fun getDrawable(context: Context, id: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= 21) {
            context.resources.getDrawable(id, context.theme)
        } else context.resources.getDrawable(id)
    }

    fun getTextFromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(html)
        }
    }

    fun changeLanguage(context: Context, language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LocaleHelper.setLocale(context, language)
        } else {
            val res = context.resources
            val dm = res.displayMetrics
            val configuration = res.configuration
            configuration.setLocale(Locale(language))
            res.updateConfiguration(configuration, dm)
        }
    }

    fun checkPlayServices(activity: Activity): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(activity)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, 0).show()
            } else {
                AppLogger.e(activity.getString(R.string.not_support_play_service))
                Toast.makeText(activity, activity.getString(R.string.device_not_support), Toast.LENGTH_SHORT).show()
                activity.finish()
            }
            return false
        }
        return true
    }

    fun setTypeface(context: Context, font: String): Typeface {
        return Typeface.createFromAsset(context.assets, font)
    }

    fun isServiceRunning(activity: Activity, serviceClass: Class<*>): Boolean {
        val manager = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context
                .CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}// this utility class is not publicy instantiable
