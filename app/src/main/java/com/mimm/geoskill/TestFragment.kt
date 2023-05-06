package com.mimm.geoskill

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


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
    private lateinit var config: TestConfig
    lateinit var ctsList: List<Country>

    lateinit var mainHeader : TextView
    lateinit var questionText : TextView
    lateinit var flagImage : ImageView
    lateinit var answHeader : TextView
    lateinit var answText : TextView
    lateinit var mapImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        config = TestConfig()
        arguments?.let {
            config.type = TestType.valueOf(it.getString("type")!!)
            config.time = it.getInt("time")
            config.count = it.getInt("count")
        }
    }

    private fun updateByTestType(v : View){
        mainHeader = v.findViewById(R.id.ctStart)
        questionText = v.findViewById(R.id.startNameTV)
        flagImage = v.findViewById(R.id.flagIV)
        answHeader = v.findViewById(R.id.needPropTV)
        answText = v.findViewById(R.id.needNameTV)
        mapImage = v.findViewById(R.id.mapIV)
        if (countries == null) {
            var gs = GsonBuilder().create()
            val listType = object : TypeToken<List<Country?>?>() {}.rawType
            countries = gs.fromJson(ctsXML, listType) as List<Country>?
        }
        ctsList = countries!!
        when (config.type) {
            TestType.Capital -> {
                questionText.visibility = View.VISIBLE;
                answText.visibility = View.VISIBLE;
            }

            TestType.Flag -> {
                mainHeader.text = "Флаг"
                answHeader.text = "Страна"
                flagImage.visibility = View.VISIBLE;
                answText.visibility = View.VISIBLE;
            }

            TestType.Map -> {
                answHeader.text = "На карте"
                questionText.visibility = View.VISIBLE;
                mapImage.visibility = View.VISIBLE;
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
        public var countries: List<Country>? = null
    }

    public val ctsXML = """

[
      {
        "Name": "Абхазия",
        "Capital": "Сухум",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%B1%D1%85%D0%B0%D0%B7%D0%B8%D1%8F",
        "FlagLink": "img/country/136flag.png",
        "MapLink": "img/country/136globe.png"
      },
      {
        "Name": "Австралия",
        "Capital": "Канберра",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%B2%D1%81%D1%82%D1%80%D0%B0%D0%BB%D0%B8%D1%8F",
        "FlagLink": "img/country/27flag.png",
        "MapLink": "img/country/27globe.png"
      },
      {
        "Name": "Австрия",
        "Capital": "Вена",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%B2%D1%81%D1%82%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/109flag.png",
        "MapLink": "img/country/109globe.png"
      },
      {
        "Name": "Азербайджан",
        "Capital": "Баку",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%B7%D0%B5%D1%80%D0%B1%D0%B0%D0%B9%D0%B4%D0%B6%D0%B0%D0%BD",
        "FlagLink": "img/country/107flag.png",
        "MapLink": "img/country/107globe.png"
      },
      {
        "Name": "Албания",
        "Capital": "Тирана",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%BB%D0%B1%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/152flag.png",
        "MapLink": "img/country/152globe.png"
      },
      {
        "Name": "Алжир",
        "Capital": "Алжир",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%BB%D0%B6%D0%B8%D1%80",
        "FlagLink": "img/country/53flag.png",
        "MapLink": "img/country/53globe.png"
      },
      {
        "Name": "Ангола",
        "Capital": "Луанда",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%BD%D0%B3%D0%BE%D0%BB%D0%B0",
        "FlagLink": "img/country/76flag.png",
        "MapLink": "img/country/76globe.png"
      },
      {
        "Name": "Андорра",
        "Capital": "Андорра-ла-Велья",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%BD%D0%B4%D0%BE%D1%80%D1%80%D0%B0",
        "FlagLink": "img/country/16flag.png",
        "MapLink": "img/country/16globe.png"
      },
      {
        "Name": "Антигуа и Барбуда",
        "Capital": "Сент-Джонс",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D0%BD%D1%82%D0%B8%D0%B3%D1%83%D0%B0%20%D0%B8%20%D0%91%D0%B0%D1%80%D0%B1%D1%83%D0%B4%D0%B0",
        "FlagLink": "img/country/18flag.png",
        "MapLink": "img/country/18globe.png"
      },
      {
        "Name": "Аргентина",
        "Capital": "Буэнос-Айрес",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D1%80%D0%B3%D0%B5%D0%BD%D1%82%D0%B8%D0%BD%D0%B0",
        "FlagLink": "img/country/51flag.png",
        "MapLink": "img/country/51globe.png"
      },
      {
        "Name": "Армения",
        "Capital": "Ереван",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D1%80%D0%BC%D0%B5%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/148flag.png",
        "MapLink": "img/country/148globe.png"
      },
      {
        "Name": "Афганистан",
        "Capital": "Кабул",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%90%D1%84%D0%B3%D0%B0%D0%BD%D0%B8%D1%81%D1%82%D0%B0%D0%BD",
        "FlagLink": "img/country/57flag.png",
        "MapLink": "img/country/57globe.png"
      },
      {
        "Name": "Багамские Острова",
        "Capital": "Нассау",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B0%D0%B3%D0%B0%D0%BC%D1%81%D0%BA%D0%B8%D0%B5%20%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0",
        "FlagLink": "img/country/186flag.png",
        "MapLink": "img/country/186globe.png"
      },
      {
        "Name": "Бангладеш",
        "Capital": "Дакка",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B0%D0%BD%D0%B3%D0%BB%D0%B0%D0%B4%D0%B5%D1%88",
        "FlagLink": "img/country/30flag.png",
        "MapLink": "img/country/30globe.png"
      },
      {
        "Name": "Барбадос",
        "Capital": "Бриджтаун",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B0%D1%80%D0%B1%D0%B0%D0%B4%D0%BE%D1%81",
        "FlagLink": "img/country/190flag.png",
        "MapLink": "img/country/190globe.png"
      },
      {
        "Name": "Бахрейн",
        "Capital": "Манама",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B0%D1%85%D1%80%D0%B5%D0%B9%D0%BD",
        "FlagLink": "img/country/169flag.png",
        "MapLink": "img/country/169globe.png"
      },
      {
        "Name": "Белиз",
        "Capital": "Бельмопан",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B5%D0%BB%D0%B8%D0%B7",
        "FlagLink": "img/country/188flag.png",
        "MapLink": "img/country/188globe.png"
      },
      {
        "Name": "Белоруссия",
        "Capital": "Минск",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B5%D0%BB%D0%BE%D1%80%D1%83%D1%81%D1%81%D0%B8%D1%8F",
        "FlagLink": "img/country/13flag.png",
        "MapLink": "img/country/13globe.png"
      },
      {
        "Name": "Бельгия",
        "Capital": "Брюссель",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B5%D0%BB%D1%8C%D0%B3%D0%B8%D1%8F",
        "FlagLink": "img/country/93flag.png",
        "MapLink": "img/country/93globe.png"
      },
      {
        "Name": "Бенин",
        "Capital": "Порто-Ново",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%B5%D0%BD%D0%B8%D0%BD",
        "FlagLink": "img/country/106flag.png",
        "MapLink": "img/country/106globe.png"
      },
      {
        "Name": "Болгария",
        "Capital": "София",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%BE%D0%BB%D0%B3%D0%B0%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/115flag.png",
        "MapLink": "img/country/115globe.png"
      },
      {
        "Name": "Боливия",
        "Capital": "Сукре",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%BE%D0%BB%D0%B8%D0%B2%D0%B8%D1%8F",
        "FlagLink": "img/country/100flag.png",
        "MapLink": "img/country/100globe.png"
      },
      {
        "Name": "Босния и Герцеговина",
        "Capital": "Сараево",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%BE%D1%81%D0%BD%D0%B8%D1%8F%20%D0%B8%20%D0%93%D0%B5%D1%80%D1%86%D0%B5%D0%B3%D0%BE%D0%B2%D0%B8%D0%BD%D0%B0",
        "FlagLink": "img/country/144flag.png",
        "MapLink": "img/country/144globe.png"
      },
      {
        "Name": "Ботсвана",
        "Capital": "Габороне",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D0%BE%D1%82%D1%81%D0%B2%D0%B0%D0%BD%D0%B0",
        "FlagLink": "img/country/161flag.png",
        "MapLink": "img/country/161globe.png"
      },
      {
        "Name": "Бразилия",
        "Capital": "Бразилиа",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D1%80%D0%B0%D0%B7%D0%B8%D0%BB%D0%B8%D1%8F",
        "FlagLink": "img/country/26flag.png",
        "MapLink": "img/country/26globe.png"
      },
      {
        "Name": "Бруней",
        "Capital": "Бандар-Сери-Бегаван",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D1%80%D1%83%D0%BD%D0%B5%D0%B9",
        "FlagLink": "img/country/185flag.png",
        "MapLink": "img/country/185globe.png"
      },
      {
        "Name": "Буркина-Фасо",
        "Capital": "Уагадугу",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D1%83%D1%80%D0%BA%D0%B8%D0%BD%D0%B0-%D0%A4%D0%B0%D1%81%D0%BE",
        "FlagLink": "img/country/77flag.png",
        "MapLink": "img/country/77globe.png"
      },
      {
        "Name": "Бурунди",
        "Capital": "Гитега",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D1%83%D1%80%D1%83%D0%BD%D0%B4%D0%B8",
        "FlagLink": "img/country/108flag.png",
        "MapLink": "img/country/108globe.png"
      },
      {
        "Name": "Бутан",
        "Capital": "Тхимпху",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%91%D1%83%D1%82%D0%B0%D0%BD",
        "FlagLink": "img/country/177flag.png",
        "MapLink": "img/country/177globe.png"
      },
      {
        "Name": "Вануату",
        "Capital": "Порт-Вила",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D0%B0%D0%BD%D1%83%D0%B0%D1%82%D1%83",
        "FlagLink": "img/country/191flag.png",
        "MapLink": "img/country/191globe.png"
      },
      {
        "Name": "Ватикан",
        "Capital": "Ватикан",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D0%B0%D1%82%D0%B8%D0%BA%D0%B0%D0%BD",
        "FlagLink": "img/country/1flag.png",
        "MapLink": "img/country/1globe.png"
      },
      {
        "Name": "Великобритания",
        "Capital": "Лондон",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D0%B5%D0%BB%D0%B8%D0%BA%D0%BE%D0%B1%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/42flag.png",
        "MapLink": "img/country/42globe.png"
      },
      {
        "Name": "Венгрия",
        "Capital": "Будапешт",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D0%B5%D0%BD%D0%B3%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/102flag.png",
        "MapLink": "img/country/102globe.png"
      },
      {
        "Name": "Венесуэла",
        "Capital": "Каракас",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D0%B5%D0%BD%D0%B5%D1%81%D1%83%D1%8D%D0%BB%D0%B0",
        "FlagLink": "img/country/64flag.png",
        "MapLink": "img/country/64globe.png"
      },
      {
        "Name": "Восточный Тимор",
        "Capital": "Дили",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D0%BE%D1%81%D1%82%D0%BE%D1%87%D0%BD%D1%8B%D0%B9%20%D0%A2%D0%B8%D0%BC%D0%BE%D1%80",
        "FlagLink": "img/country/171flag.png",
        "MapLink": "img/country/171globe.png"
      },
      {
        "Name": "Вьетнам",
        "Capital": "Ханой",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%92%D1%8C%D0%B5%D1%82%D0%BD%D0%B0%D0%BC",
        "FlagLink": "img/country/33flag.png",
        "MapLink": "img/country/33globe.png"
      },
      {
        "Name": "Габон",
        "Capital": "Либревиль",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B0%D0%B1%D0%BE%D0%BD",
        "FlagLink": "img/country/165flag.png",
        "MapLink": "img/country/165globe.png"
      },
      {
        "Name": "Гаити",
        "Capital": "Порт-о-Пренс",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B0%D0%B8%D1%82%D0%B8",
        "FlagLink": "img/country/99flag.png",
        "MapLink": "img/country/99globe.png"
      },
      {
        "Name": "Гайана",
        "Capital": "Джорджтаун",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B0%D0%B9%D0%B0%D0%BD%D0%B0",
        "FlagLink": "img/country/175flag.png",
        "MapLink": "img/country/175globe.png"
      },
      {
        "Name": "Гамбия",
        "Capital": "Банжул",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B0%D0%BC%D0%B1%D0%B8%D1%8F",
        "FlagLink": "img/country/162flag.png",
        "MapLink": "img/country/162globe.png"
      },
      {
        "Name": "Гана",
        "Capital": "Аккра",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B0%D0%BD%D0%B0",
        "FlagLink": "img/country/67flag.png",
        "MapLink": "img/country/67globe.png"
      },
      {
        "Name": "Гватемала",
        "Capital": "Гватемала",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B2%D0%B0%D1%82%D0%B5%D0%BC%D0%B0%D0%BB%D0%B0",
        "FlagLink": "img/country/83flag.png",
        "MapLink": "img/country/83globe.png"
      },
      {
        "Name": "Гвинея",
        "Capital": "Конакри",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F",
        "FlagLink": "img/country/98flag.png",
        "MapLink": "img/country/98globe.png"
      },
      {
        "Name": "Гвинея-Бисау",
        "Capital": "Бисау",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F-%D0%91%D0%B8%D1%81%D0%B0%D1%83",
        "FlagLink": "img/country/164flag.png",
        "MapLink": "img/country/164globe.png"
      },
      {
        "Name": "Германия",
        "Capital": "Берлин",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%B5%D1%80%D0%BC%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/36flag.png",
        "MapLink": "img/country/36globe.png"
      },
      {
        "Name": "Гондурас",
        "Capital": "Тегусигальпа",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%BE%D0%BD%D0%B4%D1%83%D1%80%D0%B0%D1%81",
        "FlagLink": "img/country/114flag.png",
        "MapLink": "img/country/114globe.png"
      },
      {
        "Name": "Государство Палестина",
        "Capital": "Иерусалим (Рамалла)",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D0%BE%D1%81%D1%83%D0%B4%D0%B0%D1%80%D1%81%D1%82%D0%B2%D0%BE%20%D0%9F%D0%B0%D0%BB%D0%B5%D1%81%D1%82%D0%B8%D0%BD%D0%B0",
        "FlagLink": "img/country/197flag.png",
        "MapLink": "img/country/197globe.png"
      },
      {
        "Name": "Гренада",
        "Capital": "Сент-Джорджес",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D1%80%D0%B5%D0%BD%D0%B0%D0%B4%D0%B0",
        "FlagLink": "img/country/21flag.png",
        "MapLink": "img/country/21globe.png"
      },
      {
        "Name": "Греция",
        "Capital": "Афины",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D1%80%D0%B5%D1%86%D0%B8%D1%8F",
        "FlagLink": "img/country/90flag.png",
        "MapLink": "img/country/90globe.png"
      },
      {
        "Name": "Грузия",
        "Capital": "Тбилиси",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%93%D1%80%D1%83%D0%B7%D0%B8%D1%8F",
        "FlagLink": "img/country/135flag.png",
        "MapLink": "img/country/135globe.png"
      },
      {
        "Name": "Дания",
        "Capital": "Копенгаген",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%94%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/125flag.png",
        "MapLink": "img/country/125globe.png"
      },
      {
        "Name": "Джибути",
        "Capital": "Джибути",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%94%D0%B6%D0%B8%D0%B1%D1%83%D1%82%D0%B8",
        "FlagLink": "img/country/172flag.png",
        "MapLink": "img/country/172globe.png"
      },
      {
        "Name": "Доминика",
        "Capital": "Розо",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%94%D0%BE%D0%BC%D0%B8%D0%BD%D0%B8%D0%BA%D0%B0",
        "FlagLink": "img/country/15flag.png",
        "MapLink": "img/country/15globe.png"
      },
      {
        "Name": "Доминиканская Республика",
        "Capital": "Санто-Доминго",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%94%D0%BE%D0%BC%D0%B8%D0%BD%D0%B8%D0%BA%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F%20%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0",
        "FlagLink": "img/country/101flag.png",
        "MapLink": "img/country/101globe.png"
      },
      {
        "Name": "ДР Конго",
        "Capital": "Киншаса",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%94%D0%A0%20%D0%9A%D0%BE%D0%BD%D0%B3%D0%BE",
        "FlagLink": "img/country/39flag.png",
        "MapLink": "img/country/39globe.png"
      },
      {
        "Name": "Египет",
        "Capital": "Каир",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%95%D0%B3%D0%B8%D0%BF%D0%B5%D1%82",
        "FlagLink": "img/country/34flag.png",
        "MapLink": "img/country/34globe.png"
      },
      {
        "Name": "Замбия",
        "Capital": "Лусака",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%97%D0%B0%D0%BC%D0%B1%D0%B8%D1%8F",
        "FlagLink": "img/country/87flag.png",
        "MapLink": "img/country/87globe.png"
      },
      {
        "Name": "Зимбабве",
        "Capital": "Хараре",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%97%D0%B8%D0%BC%D0%B1%D0%B0%D0%B1%D0%B2%D0%B5",
        "FlagLink": "img/country/89flag.png",
        "MapLink": "img/country/89globe.png"
      },
      {
        "Name": "Израиль",
        "Capital": "Иерусалим",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D0%B7%D1%80%D0%B0%D0%B8%D0%BB%D1%8C",
        "FlagLink": "img/country/112flag.png",
        "MapLink": "img/country/112globe.png"
      },
      {
        "Name": "Индия",
        "Capital": "Нью-Дели",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D0%BD%D0%B4%D0%B8%D1%8F",
        "FlagLink": "img/country/24flag.png",
        "MapLink": "img/country/24globe.png"
      },
      {
        "Name": "Индонезия",
        "Capital": "Джакарта",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D0%BD%D0%B4%D0%BE%D0%BD%D0%B5%D0%B7%D0%B8%D1%8F",
        "FlagLink": "img/country/25flag.png",
        "MapLink": "img/country/25globe.png"
      },
      {
        "Name": "Иордания",
        "Capital": "Амман",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D0%BE%D1%80%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/120flag.png",
        "MapLink": "img/country/120globe.png"
      },
      {
        "Name": "Ирак",
        "Capital": "Багдад",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D1%80%D0%B0%D0%BA",
        "FlagLink": "img/country/55flag.png",
        "MapLink": "img/country/55globe.png"
      },
      {
        "Name": "Иран",
        "Capital": "Тегеран",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D1%80%D0%B0%D0%BD",
        "FlagLink": "img/country/37flag.png",
        "MapLink": "img/country/37globe.png"
      },
      {
        "Name": "Ирландия",
        "Capital": "Дублин",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D1%80%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F",
        "FlagLink": "img/country/134flag.png",
        "MapLink": "img/country/134globe.png"
      },
      {
        "Name": "Исландия",
        "Capital": "Рейкьявик",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D1%81%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F",
        "FlagLink": "img/country/189flag.png",
        "MapLink": "img/country/189globe.png"
      },
      {
        "Name": "Испания",
        "Capital": "Мадрид",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D1%81%D0%BF%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/49flag.png",
        "MapLink": "img/country/49globe.png"
      },
      {
        "Name": "Италия",
        "Capital": "Рим",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%98%D1%82%D0%B0%D0%BB%D0%B8%D1%8F",
        "FlagLink": "img/country/43flag.png",
        "MapLink": "img/country/43globe.png"
      },
      {
        "Name": "Йемен",
        "Capital": "Сана",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%99%D0%B5%D0%BC%D0%B5%D0%BD",
        "FlagLink": "img/country/66flag.png",
        "MapLink": "img/country/66globe.png"
      },
      {
        "Name": "Кабо-Верде",
        "Capital": "Прая",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B0%D0%B1%D0%BE-%D0%92%D0%B5%D1%80%D0%B4%D0%B5",
        "FlagLink": "img/country/183flag.png",
        "MapLink": "img/country/183globe.png"
      },
      {
        "Name": "Казахстан",
        "Capital": "Астана",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B0%D0%B7%D0%B0%D1%85%D1%81%D1%82%D0%B0%D0%BD",
        "FlagLink": "img/country/79flag.png",
        "MapLink": "img/country/79globe.png"
      },
      {
        "Name": "Камбоджа",
        "Capital": "Пномпень",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B0%D0%BC%D0%B1%D0%BE%D0%B4%D0%B6%D0%B0",
        "FlagLink": "img/country/86flag.png",
        "MapLink": "img/country/86globe.png"
      },
      {
        "Name": "Камерун",
        "Capital": "Яунде",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B0%D0%BC%D0%B5%D1%80%D1%83%D0%BD",
        "FlagLink": "img/country/75flag.png",
        "MapLink": "img/country/75globe.png"
      },
      {
        "Name": "Канада",
        "Capital": "Оттава",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B0%D0%BD%D0%B0%D0%B4%D0%B0",
        "FlagLink": "img/country/56flag.png",
        "MapLink": "img/country/56globe.png"
      },
      {
        "Name": "Катар",
        "Capital": "Доха",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B0%D1%82%D0%B0%D1%80",
        "FlagLink": "img/country/163flag.png",
        "MapLink": "img/country/163globe.png"
      },
      {
        "Name": "Кения",
        "Capital": "Найроби",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B5%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/50flag.png",
        "MapLink": "img/country/50globe.png"
      },
      {
        "Name": "Кипр",
        "Capital": "Никосия",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B8%D0%BF%D1%80",
        "FlagLink": "img/country/174flag.png",
        "MapLink": "img/country/174globe.png"
      },
      {
        "Name": "Киргизия",
        "Capital": "Бишкек",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B8%D1%80%D0%B3%D0%B8%D0%B7%D0%B8%D1%8F",
        "FlagLink": "img/country/126flag.png",
        "MapLink": "img/country/126globe.png"
      },
      {
        "Name": "Кирибати",
        "Capital": "Южная Тарава",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B8%D1%80%D0%B8%D0%B1%D0%B0%D1%82%D0%B8",
        "FlagLink": "img/country/19flag.png",
        "MapLink": "img/country/19globe.png"
      },
      {
        "Name": "Китай",
        "Capital": "Пекин",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%B8%D1%82%D0%B0%D0%B9",
        "FlagLink": "img/country/9flag.png",
        "MapLink": "img/country/9globe.png"
      },
      {
        "Name": "КНДР",
        "Capital": "Пхеньян",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%9D%D0%94%D0%A0",
        "FlagLink": "img/country/68flag.png",
        "MapLink": "img/country/68globe.png"
      },
      {
        "Name": "Колумбия",
        "Capital": "Богота",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%BE%D0%BB%D1%83%D0%BC%D0%B1%D0%B8%D1%8F",
        "FlagLink": "img/country/48flag.png",
        "MapLink": "img/country/48globe.png"
      },
      {
        "Name": "Коморские Острова",
        "Capital": "Морони",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%BE%D0%BC%D0%BE%D1%80%D1%81%D0%BA%D0%B8%D0%B5%20%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0",
        "FlagLink": "img/country/176flag.png",
        "MapLink": "img/country/176globe.png"
      },
      {
        "Name": "Коста-Рика",
        "Capital": "Сан-Хосе",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%BE%D1%81%D1%82%D0%B0-%D0%A0%D0%B8%D0%BA%D0%B0",
        "FlagLink": "img/country/140flag.png",
        "MapLink": "img/country/140globe.png"
      },
      {
        "Name": "Кот-д'Ивуар",
        "Capital": "Ямусукро",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D0%BE%D1%82-%D0%B4%D0%98%D0%B2%D1%83%D0%B0%D1%80",
        "FlagLink": "img/country/74flag.png",
        "MapLink": "img/country/74globe.png"
      },
      {
        "Name": "Куба",
        "Capital": "Гавана",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D1%83%D0%B1%D0%B0",
        "FlagLink": "img/country/92flag.png",
        "MapLink": "img/country/92globe.png"
      },
      {
        "Name": "Кувейт",
        "Capital": "Эль-Кувейт",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9A%D1%83%D0%B2%D0%B5%D0%B9%D1%82",
        "FlagLink": "img/country/151flag.png",
        "MapLink": "img/country/151globe.png"
      },
      {
        "Name": "Лаос",
        "Capital": "Вьентьян",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B0%D0%BE%D1%81",
        "FlagLink": "img/country/118flag.png",
        "MapLink": "img/country/118globe.png"
      },
      {
        "Name": "Латвия",
        "Capital": "Рига",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B0%D1%82%D0%B2%D0%B8%D1%8F",
        "FlagLink": "img/country/160flag.png",
        "MapLink": "img/country/160globe.png"
      },
      {
        "Name": "Лесото",
        "Capital": "Масеру",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B5%D1%81%D0%BE%D1%82%D0%BE",
        "FlagLink": "img/country/157flag.png",
        "MapLink": "img/country/157globe.png"
      },
      {
        "Name": "Либерия",
        "Capital": "Монровия",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B8%D0%B1%D0%B5%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/145flag.png",
        "MapLink": "img/country/145globe.png"
      },
      {
        "Name": "Ливан",
        "Capital": "Бейрут",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B8%D0%B2%D0%B0%D0%BD",
        "FlagLink": "img/country/142flag.png",
        "MapLink": "img/country/142globe.png"
      },
      {
        "Name": "Ливия",
        "Capital": "Триполи",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B8%D0%B2%D0%B8%D1%8F",
        "FlagLink": "img/country/117flag.png",
        "MapLink": "img/country/117globe.png"
      },
      {
        "Name": "Литва",
        "Capital": "Вильнюс",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B8%D1%82%D0%B2%D0%B0",
        "FlagLink": "img/country/150flag.png",
        "MapLink": "img/country/150globe.png"
      },
      {
        "Name": "Лихтенштейн",
        "Capital": "Вадуц",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D0%B8%D1%85%D1%82%D0%B5%D0%BD%D1%88%D1%82%D0%B5%D0%B9%D0%BD",
        "FlagLink": "img/country/10flag.png",
        "MapLink": "img/country/10globe.png"
      },
      {
        "Name": "Люксембург",
        "Capital": "Люксембург",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9B%D1%8E%D0%BA%D1%81%D0%B5%D0%BC%D0%B1%D1%83%D1%80%D0%B3",
        "FlagLink": "img/country/182flag.png",
        "MapLink": "img/country/182globe.png"
      },
      {
        "Name": "Маврикий",
        "Capital": "Порт-Луи",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%B2%D1%80%D0%B8%D0%BA%D0%B8%D0%B9",
        "FlagLink": "img/country/168flag.png",
        "MapLink": "img/country/168globe.png"
      },
      {
        "Name": "Мавритания",
        "Capital": "Нуакшот",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%B2%D1%80%D0%B8%D1%82%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/147flag.png",
        "MapLink": "img/country/147globe.png"
      },
      {
        "Name": "Мадагаскар",
        "Capital": "Антананариву",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%B4%D0%B0%D0%B3%D0%B0%D1%81%D0%BA%D0%B0%D1%80",
        "FlagLink": "img/country/70flag.png",
        "MapLink": "img/country/70globe.png"
      },
      {
        "Name": "Малави",
        "Capital": "Лилонгве",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%BB%D0%B0%D0%B2%D0%B8",
        "FlagLink": "img/country/82flag.png",
        "MapLink": "img/country/82globe.png"
      },
      {
        "Name": "Малайзия",
        "Capital": "Куала-Лумпур",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%BB%D0%B0%D0%B9%D0%B7%D0%B8%D1%8F",
        "FlagLink": "img/country/63flag.png",
        "MapLink": "img/country/63globe.png"
      },
      {
        "Name": "Мали",
        "Capital": "Бамако",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%BB%D0%B8",
        "FlagLink": "img/country/84flag.png",
        "MapLink": "img/country/84globe.png"
      },
      {
        "Name": "Мальдивские Острова",
        "Capital": "Мале",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%BB%D1%8C%D0%B4%D0%B8%D0%B2%D1%81%D0%BA%D0%B8%D0%B5%20%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0",
        "FlagLink": "img/country/187flag.png",
        "MapLink": "img/country/187globe.png"
      },
      {
        "Name": "Мальта",
        "Capital": "Валлетта",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D0%BB%D1%8C%D1%82%D0%B0",
        "FlagLink": "img/country/184flag.png",
        "MapLink": "img/country/184globe.png"
      },
      {
        "Name": "Марокко",
        "Capital": "Рабат",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D1%80%D0%BE%D0%BA%D0%BA%D0%BE",
        "FlagLink": "img/country/58flag.png",
        "MapLink": "img/country/58globe.png"
      },
      {
        "Name": "Маршалловы Острова",
        "Capital": "Маджуро",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B0%D1%80%D1%88%D0%B0%D0%BB%D0%BB%D0%BE%D0%B2%D1%8B%20%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0",
        "FlagLink": "img/country/12flag.png",
        "MapLink": "img/country/12globe.png"
      },
      {
        "Name": "Мексика",
        "Capital": "Мехико",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%B5%D0%BA%D1%81%D0%B8%D0%BA%D0%B0",
        "FlagLink": "img/country/31flag.png",
        "MapLink": "img/country/31globe.png"
      },
      {
        "Name": "Мозамбик",
        "Capital": "Мапуту",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%BE%D0%B7%D0%B0%D0%BC%D0%B1%D0%B8%D0%BA",
        "FlagLink": "img/country/69flag.png",
        "MapLink": "img/country/69globe.png"
      },
      {
        "Name": "Молдавия",
        "Capital": "Кишинёв",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%BE%D0%BB%D0%B4%D0%B0%D0%B2%D0%B8%D1%8F",
        "FlagLink": "img/country/23flag.png",
        "MapLink": "img/country/23globe.png"
      },
      {
        "Name": "Монако",
        "Capital": "Монако",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%BE%D0%BD%D0%B0%D0%BA%D0%BE",
        "FlagLink": "img/country/6flag.png",
        "MapLink": "img/country/6globe.png"
      },
      {
        "Name": "Монголия",
        "Capital": "Улан-Батор",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D0%BE%D0%BD%D0%B3%D0%BE%D0%BB%D0%B8%D1%8F",
        "FlagLink": "img/country/154flag.png",
        "MapLink": "img/country/154globe.png"
      },
      {
        "Name": "Мьянма",
        "Capital": "Нейпьидо",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9C%D1%8C%D1%8F%D0%BD%D0%BC%D0%B0",
        "FlagLink": "img/country/45flag.png",
        "MapLink": "img/country/45globe.png"
      },
      {
        "Name": "Намибия",
        "Capital": "Виндхук",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B0%D0%BC%D0%B8%D0%B1%D0%B8%D1%8F",
        "FlagLink": "img/country/156flag.png",
        "MapLink": "img/country/156globe.png"
      },
      {
        "Name": "Науру",
        "Capital": "[нет столицы]",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B0%D1%83%D1%80%D1%83",
        "FlagLink": "img/country/3flag.png",
        "MapLink": "img/country/3globe.png"
      },
      {
        "Name": "Непал",
        "Capital": "Катманду",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B5%D0%BF%D0%B0%D0%BB",
        "FlagLink": "img/country/59flag.png",
        "MapLink": "img/country/59globe.png"
      },
      {
        "Name": "Нигер",
        "Capital": "Ниамей",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B8%D0%B3%D0%B5%D1%80",
        "FlagLink": "img/country/81flag.png",
        "MapLink": "img/country/81globe.png"
      },
      {
        "Name": "Нигерия",
        "Capital": "Абуджа",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B8%D0%B3%D0%B5%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/29flag.png",
        "MapLink": "img/country/29globe.png"
      },
      {
        "Name": "Нидерланды",
        "Capital": "Амстердам",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B8%D0%B4%D0%B5%D1%80%D0%BB%D0%B0%D0%BD%D0%B4%D1%8B",
        "FlagLink": "img/country/80flag.png",
        "MapLink": "img/country/80globe.png"
      },
      {
        "Name": "Никарагуа",
        "Capital": "Манагуа",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%B8%D0%BA%D0%B0%D1%80%D0%B0%D0%B3%D1%83%D0%B0",
        "FlagLink": "img/country/123flag.png",
        "MapLink": "img/country/123globe.png"
      },
      {
        "Name": "Новая Зеландия",
        "Capital": "Веллингтон",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%BE%D0%B2%D0%B0%D1%8F%20%D0%97%D0%B5%D0%BB%D0%B0%D0%BD%D0%B4%D0%B8%D1%8F",
        "FlagLink": "img/country/139flag.png",
        "MapLink": "img/country/139globe.png"
      },
      {
        "Name": "Норвегия",
        "Capital": "Осло",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9D%D0%BE%D1%80%D0%B2%D0%B5%D0%B3%D0%B8%D1%8F",
        "FlagLink": "img/country/132flag.png",
        "MapLink": "img/country/132globe.png"
      },
      {
        "Name": "ОАЭ",
        "Capital": "Абу-Даби",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9E%D0%90%D0%AD",
        "FlagLink": "img/country/133flag.png",
        "MapLink": "img/country/133globe.png"
      },
      {
        "Name": "Оман",
        "Capital": "Маскат",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9E%D0%BC%D0%B0%D0%BD",
        "FlagLink": "img/country/153flag.png",
        "MapLink": "img/country/153globe.png"
      },
      {
        "Name": "Пакистан",
        "Capital": "Исламабад",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%B0%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD",
        "FlagLink": "img/country/28flag.png",
        "MapLink": "img/country/28globe.png"
      },
      {
        "Name": "Палау",
        "Capital": "Нгерулмуд",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%B0%D0%BB%D0%B0%D1%83",
        "FlagLink": "img/country/4flag.png",
        "MapLink": "img/country/4globe.png"
      },
      {
        "Name": "Панама",
        "Capital": "Панама",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%B0%D0%BD%D0%B0%D0%BC%D0%B0",
        "FlagLink": "img/country/146flag.png",
        "MapLink": "img/country/146globe.png"
      },
      {
        "Name": "Папуа - Новая Гвинея",
        "Capital": "Порт-Морсби",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%B0%D0%BF%D1%83%D0%B0%20-%20%D0%9D%D0%BE%D0%B2%D0%B0%D1%8F%20%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F",
        "FlagLink": "img/country/116flag.png",
        "MapLink": "img/country/116globe.png"
      },
      {
        "Name": "Парагвай",
        "Capital": "Асунсьон",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%B0%D1%80%D0%B0%D0%B3%D0%B2%D0%B0%D0%B9",
        "FlagLink": "img/country/119flag.png",
        "MapLink": "img/country/119globe.png"
      },
      {
        "Name": "Перу",
        "Capital": "Лима",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%B5%D1%80%D1%83",
        "FlagLink": "img/country/61flag.png",
        "MapLink": "img/country/61globe.png"
      },
      {
        "Name": "Польша",
        "Capital": "Варшава",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%BE%D0%BB%D1%8C%D1%88%D0%B0",
        "FlagLink": "img/country/52flag.png",
        "MapLink": "img/country/52globe.png"
      },
      {
        "Name": "Португалия",
        "Capital": "Лиссабон",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%9F%D0%BE%D1%80%D1%82%D1%83%D0%B3%D0%B0%D0%BB%D0%B8%D1%8F",
        "FlagLink": "img/country/96flag.png",
        "MapLink": "img/country/96globe.png"
      },
      {
        "Name": "Республика Конго",
        "Capital": "Браззавиль",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0%20%D0%9A%D0%BE%D0%BD%D0%B3%D0%BE",
        "FlagLink": "img/country/143flag.png",
        "MapLink": "img/country/143globe.png"
      },
      {
        "Name": "Республика Корея",
        "Capital": "Сеул",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0%20%D0%9A%D0%BE%D1%80%D0%B5%D1%8F",
        "FlagLink": "img/country/46flag.png",
        "MapLink": "img/country/46globe.png"
      },
      {
        "Name": "Россия",
        "Capital": "Москва",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F",
        "FlagLink": "img/country/7flag.png",
        "MapLink": "img/country/7globe.png"
      },
      {
        "Name": "Руанда",
        "Capital": "Кигали",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A0%D1%83%D0%B0%D0%BD%D0%B4%D0%B0",
        "FlagLink": "img/country/94flag.png",
        "MapLink": "img/country/94globe.png"
      },
      {
        "Name": "Румыния",
        "Capital": "Бухарест",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A0%D1%83%D0%BC%D1%8B%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/71flag.png",
        "MapLink": "img/country/71globe.png"
      },
      {
        "Name": "Сальвадор",
        "Capital": "Сан-Сальвадор",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B0%D0%BB%D1%8C%D0%B2%D0%B0%D0%B4%D0%BE%D1%80",
        "FlagLink": "img/country/121flag.png",
        "MapLink": "img/country/121globe.png"
      },
      {
        "Name": "Самоа",
        "Capital": "Апиа",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B0%D0%BC%D0%BE%D0%B0",
        "FlagLink": "img/country/192flag.png",
        "MapLink": "img/country/192globe.png"
      },
      {
        "Name": "Сан-Марино",
        "Capital": "Сан-Марино",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B0%D0%BD-%D0%9C%D0%B0%D1%80%D0%B8%D0%BD%D0%BE",
        "FlagLink": "img/country/5flag.png",
        "MapLink": "img/country/5globe.png"
      },
      {
        "Name": "Сан-Томе и Принсипи",
        "Capital": "Сан-Томе",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B0%D0%BD-%D0%A2%D0%BE%D0%BC%D0%B5%20%D0%B8%20%D0%9F%D1%80%D0%B8%D0%BD%D1%81%D0%B8%D0%BF%D0%B8",
        "FlagLink": "img/country/194flag.png",
        "MapLink": "img/country/194globe.png"
      },
      {
        "Name": "Саудовская Аравия",
        "Capital": "Эр-Рияд",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B0%D1%83%D0%B4%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F%20%D0%90%D1%80%D0%B0%D0%B2%D0%B8%D1%8F",
        "FlagLink": "img/country/65flag.png",
        "MapLink": "img/country/65globe.png"
      },
      {
        "Name": "Северная Македония",
        "Capital": "Скопье",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D0%B2%D0%B5%D1%80%D0%BD%D0%B0%D1%8F%20%D0%9C%D0%B0%D0%BA%D0%B5%D0%B4%D0%BE%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/159flag.png",
        "MapLink": "img/country/159globe.png"
      },
      {
        "Name": "Сейшельские Острова",
        "Capital": "Виктория",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D0%B9%D1%88%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D0%B8%D0%B5%20%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0",
        "FlagLink": "img/country/17flag.png",
        "MapLink": "img/country/17globe.png"
      },
      {
        "Name": "Сенегал",
        "Capital": "Дакар",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D0%BD%D0%B5%D0%B3%D0%B0%D0%BB",
        "FlagLink": "img/country/88flag.png",
        "MapLink": "img/country/88globe.png"
      },
      {
        "Name": "Сент-Винсент и Гренадины",
        "Capital": "Кингстаун",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D0%BD%D1%82-%D0%92%D0%B8%D0%BD%D1%81%D0%B5%D0%BD%D1%82%20%D0%B8%20%D0%93%D1%80%D0%B5%D0%BD%D0%B0%D0%B4%D0%B8%D0%BD%D1%8B",
        "FlagLink": "img/country/22flag.png",
        "MapLink": "img/country/22globe.png"
      },
      {
        "Name": "Сент-Китс и Невис",
        "Capital": "Бастер",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D0%BD%D1%82-%D0%9A%D0%B8%D1%82%D1%81%20%D0%B8%20%D0%9D%D0%B5%D0%B2%D0%B8%D1%81",
        "FlagLink": "img/country/11flag.png",
        "MapLink": "img/country/11globe.png"
      },
      {
        "Name": "Сент-Люсия",
        "Capital": "Кастри",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D0%BD%D1%82-%D0%9B%D1%8E%D1%81%D0%B8%D1%8F",
        "FlagLink": "img/country/193flag.png",
        "MapLink": "img/country/193globe.png"
      },
      {
        "Name": "Сербия",
        "Capital": "Белград",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B5%D1%80%D0%B1%D0%B8%D1%8F",
        "FlagLink": "img/country/103flag.png",
        "MapLink": "img/country/103globe.png"
      },
      {
        "Name": "Сингапур",
        "Capital": "Сингапур",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B8%D0%BD%D0%B3%D0%B0%D0%BF%D1%83%D1%80",
        "FlagLink": "img/country/130flag.png",
        "MapLink": "img/country/130globe.png"
      },
      {
        "Name": "Сирия",
        "Capital": "Дамаск",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%B8%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/73flag.png",
        "MapLink": "img/country/73globe.png"
      },
      {
        "Name": "Словакия",
        "Capital": "Братислава",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%BB%D0%BE%D0%B2%D0%B0%D0%BA%D0%B8%D1%8F",
        "FlagLink": "img/country/127flag.png",
        "MapLink": "img/country/127globe.png"
      },
      {
        "Name": "Словения",
        "Capital": "Любляна",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%BB%D0%BE%D0%B2%D0%B5%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/158flag.png",
        "MapLink": "img/country/158globe.png"
      },
      {
        "Name": "Соломоновы Острова",
        "Capital": "Хониара",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%BE%D0%BB%D0%BE%D0%BC%D0%BE%D0%BD%D0%BE%D0%B2%D1%8B%20%D0%9E%D1%81%D1%82%D1%80%D0%BE%D0%B2%D0%B0",
        "FlagLink": "img/country/180flag.png",
        "MapLink": "img/country/180globe.png"
      },
      {
        "Name": "Сомали",
        "Capital": "Могадишо",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%BE%D0%BC%D0%B0%D0%BB%D0%B8",
        "FlagLink": "img/country/104flag.png",
        "MapLink": "img/country/104globe.png"
      },
      {
        "Name": "Судан",
        "Capital": "Хартум",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D1%83%D0%B4%D0%B0%D0%BD",
        "FlagLink": "img/country/60flag.png",
        "MapLink": "img/country/60globe.png"
      },
      {
        "Name": "Суринам",
        "Capital": "Парамарибо",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D1%83%D1%80%D0%B8%D0%BD%D0%B0%D0%BC",
        "FlagLink": "img/country/181flag.png",
        "MapLink": "img/country/181globe.png"
      },
      {
        "Name": "США",
        "Capital": "Вашингтон",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D0%A8%D0%90",
        "FlagLink": "img/country/8flag.png",
        "MapLink": "img/country/8globe.png"
      },
      {
        "Name": "Сьерра-Леоне",
        "Capital": "Фритаун",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A1%D1%8C%D0%B5%D1%80%D1%80%D0%B0-%D0%9B%D0%B5%D0%BE%D0%BD%D0%B5",
        "FlagLink": "img/country/122flag.png",
        "MapLink": "img/country/122globe.png"
      },
      {
        "Name": "Таджикистан",
        "Capital": "Душанбе",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D0%B0%D0%B4%D0%B6%D0%B8%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD",
        "FlagLink": "img/country/113flag.png",
        "MapLink": "img/country/113globe.png"
      },
      {
        "Name": "Таиланд",
        "Capital": "Бангкок",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D0%B0%D0%B8%D0%BB%D0%B0%D0%BD%D0%B4",
        "FlagLink": "img/country/40flag.png",
        "MapLink": "img/country/40globe.png"
      },
      {
        "Name": "Танзания",
        "Capital": "Додома",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D0%B0%D0%BD%D0%B7%D0%B0%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/47flag.png",
        "MapLink": "img/country/47globe.png"
      },
      {
        "Name": "Того",
        "Capital": "Ломе",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D0%BE%D0%B3%D0%BE",
        "FlagLink": "img/country/124flag.png",
        "MapLink": "img/country/124globe.png"
      },
      {
        "Name": "Тонга",
        "Capital": "Нукуалофа",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D0%BE%D0%BD%D0%B3%D0%B0",
        "FlagLink": "img/country/20flag.png",
        "MapLink": "img/country/20globe.png"
      },
      {
        "Name": "Тринидад и Тобаго",
        "Capital": "Порт-оф-Спейн",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D1%80%D0%B8%D0%BD%D0%B8%D0%B4%D0%B0%D0%B4%20%D0%B8%20%D0%A2%D0%BE%D0%B1%D0%B0%D0%B3%D0%BE",
        "FlagLink": "img/country/167flag.png",
        "MapLink": "img/country/167globe.png"
      },
      {
        "Name": "Тувалу",
        "Capital": "Фунафути",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D1%83%D0%B2%D0%B0%D0%BB%D1%83",
        "FlagLink": "img/country/2flag.png",
        "MapLink": "img/country/2globe.png"
      },
      {
        "Name": "Тунис",
        "Capital": "Тунис",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D1%83%D0%BD%D0%B8%D1%81",
        "FlagLink": "img/country/95flag.png",
        "MapLink": "img/country/95globe.png"
      },
      {
        "Name": "Туркмения",
        "Capital": "Ашхабад",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D1%83%D1%80%D0%BA%D0%BC%D0%B5%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/131flag.png",
        "MapLink": "img/country/131globe.png"
      },
      {
        "Name": "Турция",
        "Capital": "Анкара",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A2%D1%83%D1%80%D1%86%D0%B8%D1%8F",
        "FlagLink": "img/country/38flag.png",
        "MapLink": "img/country/38globe.png"
      },
      {
        "Name": "Уганда",
        "Capital": "Кампала",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A3%D0%B3%D0%B0%D0%BD%D0%B4%D0%B0",
        "FlagLink": "img/country/54flag.png",
        "MapLink": "img/country/54globe.png"
      },
      {
        "Name": "Узбекистан",
        "Capital": "Ташкент",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A3%D0%B7%D0%B1%D0%B5%D0%BA%D0%B8%D1%81%D1%82%D0%B0%D0%BD",
        "FlagLink": "img/country/62flag.png",
        "MapLink": "img/country/62globe.png"
      },
      {
        "Name": "Украина",
        "Capital": "Киев",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A3%D0%BA%D1%80%D0%B0%D0%B8%D0%BD%D0%B0",
        "FlagLink": "img/country/14flag.png",
        "MapLink": "img/country/14globe.png"
      },
      {
        "Name": "Уругвай",
        "Capital": "Монтевидео",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A3%D1%80%D1%83%D0%B3%D0%B2%D0%B0%D0%B9",
        "FlagLink": "img/country/149flag.png",
        "MapLink": "img/country/149globe.png"
      },
      {
        "Name": "Федеративные Штаты Микронезии",
        "Capital": "Паликир",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A4%D0%B5%D0%B4%D0%B5%D1%80%D0%B0%D1%82%D0%B8%D0%B2%D0%BD%D1%8B%D0%B5%20%D0%A8%D1%82%D0%B0%D1%82%D1%8B%20%D0%9C%D0%B8%D0%BA%D1%80%D0%BE%D0%BD%D0%B5%D0%B7%D0%B8%D0%B8",
        "FlagLink": "img/country/195flag.png",
        "MapLink": "img/country/195globe.png"
      },
      {
        "Name": "Фиджи",
        "Capital": "Сува",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A4%D0%B8%D0%B4%D0%B6%D0%B8",
        "FlagLink": "img/country/173flag.png",
        "MapLink": "img/country/173globe.png"
      },
      {
        "Name": "Филиппины",
        "Capital": "Манила",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A4%D0%B8%D0%BB%D0%B8%D0%BF%D0%BF%D0%B8%D0%BD%D1%8B",
        "FlagLink": "img/country/32flag.png",
        "MapLink": "img/country/32globe.png"
      },
      {
        "Name": "Финляндия",
        "Capital": "Хельсинки",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A4%D0%B8%D0%BD%D0%BB%D1%8F%D0%BD%D0%B4%D0%B8%D1%8F",
        "FlagLink": "img/country/129flag.png",
        "MapLink": "img/country/129globe.png"
      },
      {
        "Name": "Франция",
        "Capital": "Париж",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A4%D1%80%D0%B0%D0%BD%D1%86%D0%B8%D1%8F",
        "FlagLink": "img/country/41flag.png",
        "MapLink": "img/country/41globe.png"
      },
      {
        "Name": "Хорватия",
        "Capital": "Загреб",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A5%D0%BE%D1%80%D0%B2%D0%B0%D1%82%D0%B8%D1%8F",
        "FlagLink": "img/country/141flag.png",
        "MapLink": "img/country/141globe.png"
      },
      {
        "Name": "ЦАР",
        "Capital": "Банги",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A6%D0%90%D0%A0",
        "FlagLink": "img/country/138flag.png",
        "MapLink": "img/country/138globe.png"
      },
      {
        "Name": "Чад",
        "Capital": "Нджамена",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A7%D0%B0%D0%B4",
        "FlagLink": "img/country/91flag.png",
        "MapLink": "img/country/91globe.png"
      },
      {
        "Name": "Черногория",
        "Capital": "Подгорица",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A7%D0%B5%D1%80%D0%BD%D0%BE%D0%B3%D0%BE%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/179flag.png",
        "MapLink": "img/country/179globe.png"
      },
      {
        "Name": "Чехия",
        "Capital": "Прага",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A7%D0%B5%D1%85%D0%B8%D1%8F",
        "FlagLink": "img/country/97flag.png",
        "MapLink": "img/country/97globe.png"
      },
      {
        "Name": "Чили",
        "Capital": "Сантьяго",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A7%D0%B8%D0%BB%D0%B8",
        "FlagLink": "img/country/78flag.png",
        "MapLink": "img/country/78globe.png"
      },
      {
        "Name": "Швейцария",
        "Capital": "Берн",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A8%D0%B2%D0%B5%D0%B9%D1%86%D0%B0%D1%80%D0%B8%D1%8F",
        "FlagLink": "img/country/111flag.png",
        "MapLink": "img/country/111globe.png"
      },
      {
        "Name": "Швеция",
        "Capital": "Стокгольм",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A8%D0%B2%D0%B5%D1%86%D0%B8%D1%8F",
        "FlagLink": "img/country/105flag.png",
        "MapLink": "img/country/105globe.png"
      },
      {
        "Name": "Шри-Ланка",
        "Capital": "Шри-Джаяварденепура-Котте",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%A8%D1%80%D0%B8-%D0%9B%D0%B0%D0%BD%D0%BA%D0%B0",
        "FlagLink": "img/country/72flag.png",
        "MapLink": "img/country/72globe.png"
      },
      {
        "Name": "Эквадор",
        "Capital": "Кито",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AD%D0%BA%D0%B2%D0%B0%D0%B4%D0%BE%D1%80",
        "FlagLink": "img/country/85flag.png",
        "MapLink": "img/country/85globe.png"
      },
      {
        "Name": "Экваториальная Гвинея",
        "Capital": "Малабо",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AD%D0%BA%D0%B2%D0%B0%D1%82%D0%BE%D1%80%D0%B8%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F%20%D0%93%D0%B2%D0%B8%D0%BD%D0%B5%D1%8F",
        "FlagLink": "img/country/178flag.png",
        "MapLink": "img/country/178globe.png"
      },
      {
        "Name": "Эритрея",
        "Capital": "Асмэра",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AD%D1%80%D0%B8%D1%82%D1%80%D0%B5%D1%8F",
        "FlagLink": "img/country/128flag.png",
        "MapLink": "img/country/128globe.png"
      },
      {
        "Name": "Эсватини",
        "Capital": "Мбабане",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AD%D1%81%D0%B2%D0%B0%D1%82%D0%B8%D0%BD%D0%B8",
        "FlagLink": "img/country/170flag.png",
        "MapLink": "img/country/170globe.png"
      },
      {
        "Name": "Эстония",
        "Capital": "Таллин",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AD%D1%81%D1%82%D0%BE%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/166flag.png",
        "MapLink": "img/country/166globe.png"
      },
      {
        "Name": "Эфиопия",
        "Capital": "Аддис-Абеба",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AD%D1%84%D0%B8%D0%BE%D0%BF%D0%B8%D1%8F",
        "FlagLink": "img/country/35flag.png",
        "MapLink": "img/country/35globe.png"
      },
      {
        "Name": "ЮАР",
        "Capital": "Претория",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AE%D0%90%D0%A0",
        "FlagLink": "img/country/44flag.png",
        "MapLink": "img/country/44globe.png"
      },
      {
        "Name": "Южная Осетия",
        "Capital": "Цхинвал",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AE%D0%B6%D0%BD%D0%B0%D1%8F%20%D0%9E%D1%81%D0%B5%D1%82%D0%B8%D1%8F",
        "FlagLink": "img/country/137flag.png",
        "MapLink": "img/country/137globe.png"
      },
      {
        "Name": "Южный Судан",
        "Capital": "Джуба",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AE%D0%B6%D0%BD%D1%8B%D0%B9%20%D0%A1%D1%83%D0%B4%D0%B0%D0%BD",
        "FlagLink": "img/country/110flag.png",
        "MapLink": "img/country/110globe.png"
      },
      {
        "Name": "Ямайка",
        "Capital": "Кингстон",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AF%D0%BC%D0%B0%D0%B9%D0%BA%D0%B0",
        "FlagLink": "img/country/155flag.png",
        "MapLink": "img/country/155globe.png"
      },
      {
        "Name": "Япония",
        "Capital": "Токио",
        "PageLink": "https://geo.koltyrin.ru/country.php?country=%D0%AF%D0%BF%D0%BE%D0%BD%D0%B8%D1%8F",
        "FlagLink": "img/country/196flag.png",
        "MapLink": "img/country/196globe.png"
      }
    ]
    """
}