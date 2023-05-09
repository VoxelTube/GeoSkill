package com.mimm.geoskill

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Space
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "config"

/**
 * A simple [Fragment] subclass.
 * Use the [TestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TestFragment : Fragment() {
    // TODO: Rename and change types of parameters
    public lateinit var config: TestConfig
    lateinit var ctsList: Array<Country>
    lateinit var gameHandler: TestCore

    lateinit var space: Space
    lateinit var progPB: ProgressBar
    lateinit var progTV: TextView
    lateinit var mainHeader: TextView
    lateinit var questionText: TextView
    lateinit var flagImage: ImageView
    lateinit var answHeader: TextView
    lateinit var answText: TextView
    lateinit var mapImage: ImageView
    lateinit var timeout: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = TestConfig()
        arguments?.let {
            config.type = TestType.valueOf(it.getString("type")!!)
            config.time = it.getInt("time")
            config.count = it.getInt("count")
        }
    }

    private fun updateByTestType(v: View) {
        space = v.findViewById(R.id.space)
        progPB = v.findViewById(R.id.progressBar)
        progTV = v.findViewById(R.id.progressTV)
        mainHeader = v.findViewById(R.id.ctStart)
        questionText = v.findViewById(R.id.startNameTV)
        flagImage = v.findViewById(R.id.flagIV)
        answHeader = v.findViewById(R.id.needPropTV)
        answText = v.findViewById(R.id.needNameTV)
        mapImage = v.findViewById(R.id.mapIV)
        timeout = v.findViewById(R.id.timeEndTV)
        if (countries == null) {
            var gs = GsonBuilder().create()
            var ctsStream = resources.openRawResource(R.raw.countries)
            var ctsJosn = readTextFile(ctsStream)
            countries = gs.fromJson<Array<Country>>(ctsJosn, Array<Country>::class.java)
        }
        ctsList = countries!!
        when (config.type) {
            TestType.Capital -> {
                questionText.visibility = View.VISIBLE;
                answText.visibility = View.INVISIBLE;
                space.visibility = View.VISIBLE
            }

            TestType.Flag -> {
                mainHeader.text = "Флаг"
                answHeader.text = "Страна"
                flagImage.visibility = View.VISIBLE;
                answText.visibility = View.INVISIBLE;
            }

            TestType.Map -> {
                answHeader.text = "На карте"
                questionText.visibility = View.VISIBLE;
                mapImage.visibility = View.INVISIBLE;
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_test, container, false)
        updateByTestType(v)
        return v
    }

    public fun open() {
        when (config.type) {
            TestType.Capital -> {
                answText.visibility = View.VISIBLE;
            }

            TestType.Flag -> {

                answText.visibility = View.VISIBLE;
            }

            TestType.Map -> {
                mapImage.visibility = View.VISIBLE;
            }
        }
        timeout.visibility = View.INVISIBLE
    }

    public fun setQuest(country: Country, answered: Int) {
        progTV.text = answered.toString() + "/" + config.count
        progPB.max = config.count
        progPB.progress = answered
        when (config.type) {
            TestType.Capital -> {
                questionText.text = country.Name
                answText.visibility = View.INVISIBLE;
                answText.text = country.Capital
            }

            TestType.Flag -> {
                getBitmap(country.FlagLink, flagImage, answered)
                answText.visibility = View.INVISIBLE;
                answText.text = country.Name
            }

            TestType.Map -> {
                questionText.text = country.Name
                mapImage.visibility = View.INVISIBLE;
                getBitmap(country.MapLink, mapImage, answered)
            }
        }

    }

    private fun getFileResId(resName: String): Int {
        return try {
            val res: Class<*> = R.raw::class.java
            val idField = res.getField(resName)
            idField.getInt(idField)
        } catch (exception: Exception) {
            -1
        }
    }

    fun getBitmap(location: String, imageView: ImageView, answered: Int) {

        if (location.startsWith("f")) {
            try {
                var res = resources.openRawResource(getFileResId(location.substring(1, 3)))
                var img = BitmapFactory.decodeStream(res)
                imageView.setImageBitmap(img)
            } catch (ex: Exception) {
                Log.e("ex", ex.message!! + " " + location)
            }

            var handler = Handler()
            handler.postDelayed({
                if (answered == gameHandler.answered) {
                    timeout.visibility = View.VISIBLE
                }
            }, (config.time * 1000).toLong())
        } else {
            val executor = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())
            executor.execute {
                try {
                    val url = URL(location).openStream()
                    var img = BitmapFactory.decodeStream(url)
                    handler.post {
                        imageView.setImageBitmap(img)
                        var handler = Handler()
                        handler.postDelayed({
                            if (answered == gameHandler.answered) {
                                timeout.visibility = View.VISIBLE
                            }
                        }, (config.time * 1000).toLong())
                    }
                } catch (e: Exception) {
                    Log.e("web", e.message!!)
                }

            }
        }

    }

    fun readTextFile(inputStream: InputStream): String {
        val outputStream = ByteArrayOutputStream()
        val buf = ByteArray(1024)
        var len: Int
        try {
            while (inputStream.read(buf).also { len = it } != -1) {
                outputStream.write(buf, 0, len)
            }
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
        }
        return outputStream.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TestFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(type: TestType, time: Int, count: Int) =
            TestFragment().apply {
                arguments = Bundle().apply {
                    putString("type", type.toString())
                    putInt("time", time)
                    putInt("count", count)
                }
            }

        @JvmStatic
        public var countries: Array<Country>? = null
    }
}