package adi.example.indicator

import adi.example.bezier.R
import adi.example.indicator.Constants.CHANNELS
import adi.example.indicatorlib.adapter.GreatIndicatorAdapter
import adi.example.indicatorlib.indicator.config.AbstractIndicatorConfig
import adi.example.indicatorlib.indicator.config.LineIndicatorConfig
import adi.example.indicatorlib.model.IndicatorWidthModel
import adi.example.indicatorlib.title.TextTitleConfig
import adi.example.indicatorlib.title.badge.BadgeConfig
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_indicator.*
import java.util.*

class IndicatorActivity : AppCompatActivity() {

    private var mExamplePagerAdapter: ExamplePagerAdapter? = null
    private lateinit var mBadgeNumbers: ArrayList<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator)
        val mDataList = CHANNELS.asList()
        mExamplePagerAdapter = ExamplePagerAdapter(mDataList)
        viewpager.adapter = mExamplePagerAdapter

        mBadgeNumbers = ArrayList<Int>(mDataList.size)
        randomBadgeNumbers()
        init1()
    }

    /**
     * 随机角标数字
     */
    private fun randomBadgeNumbers() {
        for (i in CHANNELS.indices) {
            mBadgeNumbers.add(Random().nextInt(120))
        }
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
                    .setPendingBottom(10)
                    .setPendingLeft(20)
                    .setPendingRight(20)
            }

            override fun getTitleName(position: Int): String {
                return Constants.CHANNELS[position]
            }

            override fun bindBageConfig(position: Int): BadgeConfig {
                return BadgeConfig.build()
                    .setBadgeColor(Color.RED)
                    .setRadius(15.0f)
                    .setTxtColor(Color.WHITE)
                    .setTxtSize(22.0f)
            }

            override fun getBadgeNumer(position: Int): Int {
                return mBadgeNumbers.get(position)
            }

        })
        greatIndicator.bindViewPager(viewpager)
    }
}
