package com.example.artcolllector

import MyFragment1
import MyFragment2
import MyFragment3
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.art_list_item.view.*
import java.time.chrono.JapaneseEra.values

private const val NUM_PAGES = 3 // 페이지 수를 정해둠
class MainActivity : AppCompatActivity()
{
//    private var currentPosition = Int.MAX_VALUE / 2
//    private var myHandler = MyHandler()
//    private val intervalTime = 1500.toLong() // 몇초 간격으로 페이지를 넘길것인지 (1500 = 1.5초)


    override fun onCreate(savedInstanceState: Bundle?)
    {
        setTheme(R.style.ArtColllector)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pagerMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth


        viewPager_artSwipe.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        viewPager_artSwipe.adapter = ScreenSlidePagerAdapter(this) // 어댑터 생성
        viewPager_artSwipe.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        viewPager_artSwipe.setPageTransformer(ZoomOutPageTransformer()) // 애니메이션 적용

        dots_indicator.setViewPager2(viewPager_artSwipe) // indicator 설정
    }
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES // 페이지 수 리턴

        override fun createFragment(position: Int): Fragment {
            return when(position){ // 페이지 포지션에 따라 그에 맞는 프래그먼트를 보여줌
                0 -> MyFragment1()
                else -> MyFragment2()
            }
        }
    }
}


// 일정시간마다 자동으로  viewpager 넘어가도록. 미완
//        viewPager_artSwipe.apply {
//             fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//                when (state) {
//                    // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
//                    ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
//                    // 뷰페이저 움직이는 중
//                    ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
//                }
//            }
//        }
//    }
//    private fun autoScrollStart(intervalTime: Long) {
//        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
//        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
//    }
//    private fun autoScrollStop(){
//        myHandler.removeMessages(0) // 핸들러를 중지시킴
//    }
//
//    private inner class MyHandler : Handler() {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//
//            if(msg.what == 0) {
//                viewPager_artSwipe.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
//                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
//            }
//        }
//    }
//
//    // 다른 페이지 갔다가 돌아오면 다시 스크롤 시작
//    override fun onResume() {
//        super.onResume()
//        autoScrollStart(intervalTime)
//    }
//
//    // 다른 페이지로 떠나있는 동안 스크롤이 동작할 필요는 없음. 정지
//    override fun onPause() {
//        super.onPause()
//        autoScrollStop()
//    }












