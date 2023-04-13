package com.simplifii.`in`

import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageInfo
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Jsoup
import java.lang.Exception
import android.content.pm.PackageManager
import android.content.DialogInterface
import android.net.Uri
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import com.mf.library.OnCallBack
import com.mf.library.UpdateChecker
import java.io.IOException
import com.google.android.play.core.install.InstallStateUpdatedListener

import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.AppUpdateType

import com.google.android.play.core.install.model.UpdateAvailability

import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.tasks.Task
import android.content.IntentSender
import android.content.IntentSender.SendIntentException
import android.R
import android.view.View

import com.google.android.material.snackbar.Snackbar





class SplashScreeenActivity :AppCompatActivity() {

    //private val FLEXIBLE_APP_UPDATE_REQ_CODE = 123
    private var appUpdateManager: AppUpdateManager? = null
    //private var installStateUpdatedListener: InstallStateUpdatedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.simplifii.R.layout.activity_splash_screen)
        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
        Handler().postDelayed({
           // checkAppStoreVersion()
            val intent = Intent(this@SplashScreeenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

    }

    private fun checkAppStoreVersion() {
        /*installStateUpdatedListener = InstallStateUpdatedListener { state: InstallState ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            } else if (state.installStatus() == InstallStatus.INSTALLED) {
                removeInstallStateUpdateListener()
            } else {
                Toast.makeText(
                    applicationContext, "InstallStateUpdatedListener: state: " + state.installStatus(), Toast.LENGTH_LONG).show()
            }
        }
        appUpdateManager!!.registerListener(installStateUpdatedListener)
        checkUpdate();*/

    /* GetVersionCode(this).execute()
       var ismainPageShow = false
        UpdateChecker(this)
            .setUpdateLabel("Update") // optional - this to edit update button (default is "Update NOW")
            .setRemindLabel("Remind me") // optional - this to edit remind button (default is "Remind me later")
            .setRemindDays(2) //optional app remind user every 2 days (default is everyday)
            .setForceCloseOnSkip(true) //optional user will choose update or close app
            .setOnCallBack(object : OnCallBack {
                override fun Done(success: Boolean, isUpdateAvailable: Boolean, new_version: String?
                ): Boolean {
                    if(isUpdateAvailable ){
                        showUpdateDialog(this@SplashScreeenActivity)
                    }else if(!ismainPageShow){
                        ismainPageShow =true
                        val intent = Intent(this@SplashScreeenActivity, MainActivity::class.java)
                         startActivity(intent)
                          finish()
                    }

                   return true
                }
            }).checkUpdate()
        UpdateChecker.clearReminder(this);*/
    }

   /* fun checkUpdate(){
        val appUpdateInfoTask: Task<AppUpdateInfo> = appUpdateManager!!.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() === UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                startUpdateFlow(appUpdateInfo)
            } else if (appUpdateInfo.installStatus() === InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            }
        }
    }

    private fun startUpdateFlow(appUpdateInfo: AppUpdateInfo) {
        try {
            appUpdateManager!!.startUpdateFlowForResult(appUpdateInfo,
                AppUpdateType.FLEXIBLE, this, FLEXIBLE_APP_UPDATE_REQ_CODE)
        } catch (e: SendIntentException) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == FLEXIBLE_APP_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Update canceled by user! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(),"Update success! Result Code: " + resultCode, Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getApplicationContext(), "Update Failed! Result Code: " + resultCode, Toast.LENGTH_LONG).show();
                checkUpdate();
            }
            val intent = Intent(this@SplashScreeenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun popupSnackBarForCompleteUpdate() {
        Snackbar.make(findViewById<View>(android.R.id.content).rootView, "New app is ready!", Snackbar.LENGTH_INDEFINITE)
            .setAction("Install") { view: View? ->
                if (appUpdateManager != null) {
                    appUpdateManager!!.completeUpdate()
                }
            }
            .setActionTextColor(resources.getColor(com.simplifii.R.color.purple_500))
            .show()
    }

    private fun removeInstallStateUpdateListener() {
        if (appUpdateManager != null) {
            appUpdateManager!!.unregisterListener(installStateUpdatedListener)
        }
    }

    override fun onStop() {
        super.onStop()
       // removeInstallStateUpdateListener()
    }*/

    public fun showUpdateDialog( splashScreeenActivity: SplashScreeenActivity) {
        var dialog: Dialog?=null
        val builder = AlertDialog.Builder(splashScreeenActivity)
        builder.setTitle("A new version Available")
        builder.setPositiveButton("Update App") { dialog, which ->
            /* startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                            ("market://details?id=com.wecompli")));*/
            val appPackageName = splashScreeenActivity.packageName // getPackageName() from Context or Activity object
            try {
                splashScreeenActivity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (anfe: android.content.ActivityNotFoundException) {
                splashScreeenActivity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel",
            object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                }
            })

        builder.setCancelable(true)
        dialog = builder.show()
        dialog!!.setCancelable(false)
    }

    class GetVersionCode(val splashScreeenActivity: SplashScreeenActivity) : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            var newVersion: String? = null
            try {
                val document =
                    Jsoup.connect("https://play.google.com/store/apps/details?id=" + splashScreeenActivity.packageName + "&hl=en_IN")
                        //  Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + "com.app.astrolab"  + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();


               /* if (document != null) {
                    val element = document!!.getElementsContainingOwnText("Current Version")
                    for (ele in element) {
                        if (ele.siblingElements() != null) {
                            val sibElemets = ele.siblingElements()
                            for (sibElemet in sibElemets) {
                                newVersion = sibElemet.text()
                            }
                        }
                    }
                }*/
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return  newVersion
        }

        override fun onPreExecute() {
            super.onPreExecute()

        }

        override fun onPostExecute(onlineVersion: String?) {
            super.onPostExecute(onlineVersion)

            if (onlineVersion != null && !onlineVersion.isEmpty()) {
                if (getcurrentVersion()?.toFloat()!! < (onlineVersion).toFloat()) {
                    //show anything
                   //showUpdateDialog()
                }

            }else{
                val intent = Intent(splashScreeenActivity, MainActivity::class.java)
                splashScreeenActivity.startActivity(intent)
                splashScreeenActivity.finish()
            }

        }



        private fun showDialogforAppupdate() {
            val dialogClickListener =
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            try {
                                val viewIntent = Intent(
                                    "android.intent.action.VIEW",
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.adeebhat.rabbitsvilla")
                                )
                                splashScreeenActivity.startActivity(viewIntent)
                            } catch (e: Exception) {
                                Toast.makeText(
                                    splashScreeenActivity, "Unable to Connect Try Again...",
                                    Toast.LENGTH_LONG
                                ).show()
                                e.printStackTrace()
                            }
                        }
                        DialogInterface.BUTTON_NEGATIVE -> {
                            dialog.dismiss()
                        }
                    }
                }

            val builder: AlertDialog.Builder = AlertDialog.Builder(splashScreeenActivity)
            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show()
        }

        private fun getcurrentVersion(): String? {
            val pm = splashScreeenActivity.packageManager
            var pInfo: PackageInfo? = null

            try {
                pInfo = pm.getPackageInfo(splashScreeenActivity.packageName, 0)
            } catch (e1: PackageManager.NameNotFoundException) {
                // TODO Auto-generated catch block
                e1.printStackTrace()
            }

          return  pInfo!!.versionName

        }
    }
}


