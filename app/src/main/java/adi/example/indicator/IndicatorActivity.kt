package adi.example.indicator

import adi.example.bezier.R
import adi.example.indicator.Constants.CHANNELS
import adi.example.indicatorlib.adapter.GreatIndicatorAdapter
import adi.example.indicatorlib.indicator.config.AbstractIndicatorConfig
import adi.example.indicatorlib.indicator.config.LineIndicatorConfig
import adi.example.indicatorlib.model.IndicatorWidthModel
import adi.example.indicatorlib.title.TextTitleConfig
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_indicator.*

class IndicatorActivity : AppCompatActivity() {

    private var mExamplePagerAdapter: ExamplePagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator)
        val mDataList = CHANNELS.asList()
        mExamplePagerAdapter = ExamplePagerAdapter(mDataList)
        viewpager.adapter = mExamplePagerAdapter
        init1()
    }

    fun init1() {
        greatIndicator.setAdapter(object : GreatIndicatorAdapter<TextTitleConfig>() {
            override fun bindTitleConfig(): TextTitleConfig {
                return TextTitleConfig.factory()
                    .setNormalColors(Constants.normalTxtColors)
                    .setSelectedColors(Constants.selectedTxtColors)
                    .setPendingLeft(20)
                    .setPendingRight(20)
                    .setNormalSize(30)
                    .setSelectedSize(40)
            }

            override fun getCount(): Int {
                return CHANNELS.size
            }

            override fun bindIndicatorConfig(): AbstractIndicatorConfig<out AbstractIndicatorConfig<*>> {
                return LineIndicatorConfig.factory()
                    .setRoundRadius(10)
                    .setWidthModel(IndicatorWidthModel.MATCH_EDGE)
                    .setColor(Constants.randomIndicatorColor())
                    .setPendingBottom(-10)
                    .setPendingLeft(20)
                    .setPendingRight(20)
            }

            override fun getTitleName(position: Int): String {
                return Constants.CHANNELS[position]
            }

        })
        greatIndicator.bindViewPager(viewpager)
    }
}
