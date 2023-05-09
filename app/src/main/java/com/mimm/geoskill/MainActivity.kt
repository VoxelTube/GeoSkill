package com.mimm.geoskill

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.findFragment
import com.mimm.geoskill.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    public var core: TestCore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun startTest(view: View) {
        var tt: TestType = TestType.Capital
        var n: Int = 1
        var time: Int = 1
        try {
            var type = findViewById<Spinner>(R.id.testTypeSelector).selectedItem as String
            n = findViewById<EditText>(R.id.countriesCountEntry).text.toString().toInt()
            time = findViewById<EditText>(R.id.timeEntry).text.toString().toInt()
            tt = TestType.values().first { it.testPoint == type }
        } catch (e: Exception) {
            return
        }
        var args = Bundle().apply {
            putString("type", tt.toString())
            putInt("time", time)
            putInt("count", n)
        }
        view.findNavController().navigate(R.id.action_LaunchFragment_to_TestFragment, args)
    }

    fun onNextStage(view: View) {
        var frag = view.findFragment() as TestFragment

        if (core == null) {

            core = TestCore(frag)
        } else if (core!!.view != frag) {
            core = TestCore(frag)
        }
        var btn = view as Button
        btn.text = core!!.next()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}