package com.bulbeon.lessonable;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
	SlidingMenu menu;
	ImageView ivHome;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setBehindContentView(R.layout.menu);
		
		//커스텀 액션바
		ActionBar mActionBar = getActionBar();
		mActionBar.setDisplayShowHomeEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(false);
		View mCustomView = View.inflate(this, R.layout.actionbar, null);
		
		//메뉴 버튼 애니메이션
		ivHome = (ImageView) mCustomView.findViewById(R.id.ivHome);
		ivHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (menu.isMenuShowing()) {
					Animation aniExpand = AnimationUtils.loadAnimation(MainActivity.this, R.anim.home_expand);
					ivHome.startAnimation(aniExpand);
				} else {
					Animation aniShrink = AnimationUtils.loadAnimation(MainActivity.this, R.anim.home_shrink);
					ivHome.startAnimation(aniShrink);
				}
				
				menu.toggle();
			}
		});

		//기존 액션바  disable
		mActionBar.setCustomView(mCustomView);
		mActionBar.setDisplayShowCustomEnabled(true);
		
		//슬라이딩 메뉴 설정
		menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu);
        
        //슬라이딩 메뉴 클릭 처리
        findViewById(R.id.menu1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "menu1 clicked", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			Animation aniExpand = AnimationUtils.loadAnimation(MainActivity.this, R.anim.home_expand);
			ivHome.startAnimation(aniExpand);
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}
}
