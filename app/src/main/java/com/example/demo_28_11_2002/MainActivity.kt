package com.example.demo_28_11_2002

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.example.demo_28_11_2002.demo_1_12_2022.task1.MainTask01Activity
import com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.RetrofitClient
import com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.TodoApi
import com.example.demo_28_11_2002.swingdata.activitytofragment.Main2Activity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AuthenListener {
    val a = 1
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient
    companion object{
        var name :String = MainActivity::class.java.simpleName
    }
    private lateinit var callBackManager: CallbackManager

    @SuppressLint("ApplySharedPref")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callBackManager = CallbackManager.Factory.create()


        auth = FirebaseAuth.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //hello.
        btnSignIn.setOnClickListener {
           validate()
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this,gso)
        btnLoginGoogle.setOnClickListener {
            signInGoogle()
        }


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(name, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            val sdf :SharedPreferences = getSharedPreferences("MYTOKEN", MODE_PRIVATE)
            val editor :SharedPreferences.Editor = sdf.edit()
            editor.putString("token",token)
            editor.commit()
            // Log and toast
//            val msg = getString(R.string.msg_token_fmt, token)
            Log.e(name, token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

        loginFaceBook()

        btnLoginWithInstagram.setOnClickListener {
            val authDialog = InstagramActivity(this,this)
            authDialog.setCancelable(true)
            authDialog.show()
        }

    }
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account: GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun updateUI(account: GoogleSignInAccount) {
        val  credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val i = Intent(this,MainTask01Activity::class.java)
                i.putExtra("name",account.displayName)
                i.putExtra("email",account.email)
                startActivity(i)

            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun validate(){
        if (tilEmail.editText?.text.toString().trim().isEmpty()){
            tilEmail.error ="Khong duoc de trong"
            tilEmail.isErrorEnabled = true
            return
        }
        else{
            tilEmail.isErrorEnabled = false
            val i = Intent(this,MainTask01Activity::class.java)
            i.putExtra("name",tilEmail.editText?.text.toString().trim())
            startActivity(i)
        }
    }

    ///fb
    private fun loginFaceBook(){
        btnFaceBook.setReadPermissions(mutableListOf("public_profile",
            "email",
            "user_gender",
            "user_birthday"))
        btnFaceBook.registerCallback(callBackManager,object : FacebookCallback<LoginResult> {
            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
            }

            override fun onSuccess(result: LoginResult) {
                val graphRequest =
                    GraphRequest.newMeRequest(result?.accessToken) { `object`, respone ->
                        getDataFaceBook(`object`)
                    }
                val parameters = Bundle()
                parameters.putString("fields", "id,email,birthday,gender,name")
                graphRequest.parameters = parameters
                graphRequest.executeAsync()
                Toast.makeText(this@MainActivity, "Thanh cong", Toast.LENGTH_SHORT).show()
            }


        })
    }
    private fun getDataFaceBook(obj: JSONObject?) {
        val profilePhoto =
            "https://graph.facebook.com/${obj?.getString("id")}/picture?width=200&height=200"
        val name = obj!!.getString("name")
        val birthday = obj?.getString("birthday")
        val gender = obj?.getString("gender")
        val email = obj?.getString("email")

        Toast.makeText(this,
            "name$name,birthday:$birthday,gender:$gender,email:$email",
            Toast.LENGTH_SHORT).show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callBackManager.onActivityResult(resultCode,resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResults(task)
        }
    }

    override fun onAuthenVerified(token: String) {
        getUserInfo(token)
    }

    private fun getUserInfo(token: String) {
        val client = RetrofitClient.instagramApi.create(TodoApi::class.java)
        val call :Call<User> = client.getUserInfo(token)
        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Toast.makeText(this@MainActivity, "Thanh cong", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "That bai", Toast.LENGTH_SHORT).show()
            }

        })
    }

    //login with instagram
}