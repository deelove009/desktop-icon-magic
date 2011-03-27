package work.dingran.myshortcut;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiyun.ad.AdView;

public class Launcher extends Activity{
	
	Animation shake;
	
	AdView ad;
	final static String LOG_TAG = "LauncherAds";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        
        shake=AnimationUtils.loadAnimation(this, R.anim.shake);
        
        RelativeLayout bigback = new RelativeLayout(this);
        bigback.setBackgroundResource(work.dingran.myshortcut.R.drawable.wallpapera);
        	RelativeLayout.LayoutParams tvv1 = new RelativeLayout.LayoutParams(
        			RelativeLayout.LayoutParams.FILL_PARENT,
        			RelativeLayout.LayoutParams.WRAP_CONTENT
        	);
        	tvv1.topMargin = 10;
        	tvv1.leftMargin = 10;
        	tvv1.rightMargin = 10;
	        TextView tv1 = new TextView(this);
	        tv1.setId(999);
	        tv1.setBackgroundColor(Color.GREEN);
	        tv1.setHeight(150);
	        tv1.setTextSize(20);
	        tv1.setText("桌面快捷方式管理器：帮助你创建各种桌面快捷方式，有效管理桌面的快捷方式图标");
        bigback.addView(tv1,tvv1);
        
        	final Button bt1 = new Button(this);
        	bt1.setId(888);
        	bt1.requestFocus();
        	bt1.setOnFocusChangeListener(new OnFocusChangeListener(){

				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
						bt1.setBackgroundColor(Color.MAGENTA);
						bt1.setShadowLayer(1, 1, 1, Color.RED);
						bt1.startAnimation(shake);
						
					}else{
						bt1.setBackgroundColor(Color.LTGRAY);
					}
					
				}
        		
        	});
        	
//        	bt1.setBackgroundColor(Color.MAGENTA);
        	bt1.setText("创建新的快捷方式");
        	bt1.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(Launcher.this, CreateShortCut.class);
					Launcher.this.startActivity(intent);
					
				}
        		
        	});
        	RelativeLayout.LayoutParams btt1 = new RelativeLayout.LayoutParams(
        			RelativeLayout.LayoutParams.FILL_PARENT,
        			RelativeLayout.LayoutParams.WRAP_CONTENT
        	);
        	btt1.topMargin = 20;
        	btt1.leftMargin = 10;
        	btt1.rightMargin = 10;
//        	btt1.addRule(RelativeLayout.BELOW,RelativeLayout.TRUE);
//        	btt1.addRule(RelativeLayout.ALIGN_TOP, RelativeLayout.TRUE);
        	btt1.addRule(RelativeLayout.BELOW,999);
        bigback.addView(bt1,btt1);	
        
	        final Button bt2 = new Button(this);
	    	bt2.setId(777);
	    	bt2.setBackgroundColor(Color.LTGRAY);
	    	bt2.setOnFocusChangeListener(new OnFocusChangeListener(){

				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
						bt2.setBackgroundColor(Color.MAGENTA);
						bt2.startAnimation(shake);
					}else{
						bt2.setBackgroundColor(Color.LTGRAY);
					}
					
				}
        		
        	});
	    	bt2.setText("删除桌面的快捷方式");
	    	bt2.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(Launcher.this, DeleteShortCut.class);
					Launcher.this.startActivity(intent);
					
				}
        		
        	});
	    	RelativeLayout.LayoutParams btt2 = new RelativeLayout.LayoutParams(
	    			RelativeLayout.LayoutParams.FILL_PARENT,
	    			RelativeLayout.LayoutParams.WRAP_CONTENT
	    	);
	    	btt2.topMargin = 20;
	    	btt2.leftMargin = 10;
	    	btt2.rightMargin = 10;
	    	btt2.addRule(RelativeLayout.BELOW,888);
	    bigback.addView(bt2,btt2);	
		    final Button bt3 = new Button(this);
	    	bt3.setId(666);
	    	bt3.setBackgroundColor(Color.LTGRAY);
	    	bt3.setOnFocusChangeListener(new OnFocusChangeListener(){

				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
						bt3.setBackgroundColor(Color.MAGENTA);
						bt3.startAnimation(shake);
					}else{
						bt3.setBackgroundColor(Color.LTGRAY);
					}
					
				}
        		
        	});
	    	bt3.setText("编辑桌面的快捷方式");
	    	RelativeLayout.LayoutParams btt3 = new RelativeLayout.LayoutParams(
	    			RelativeLayout.LayoutParams.FILL_PARENT,
	    			RelativeLayout.LayoutParams.WRAP_CONTENT
	    	);
	    	btt3.topMargin = 20;
	    	btt3.leftMargin = 10;
	    	btt3.rightMargin = 10;
	    	btt3.addRule(RelativeLayout.BELOW,777);
//	    bigback.addView(bt3,btt3);
	    	
	    	ad = new AdView(this);
	    	ad.setResId("");// 这里是我的微云序列号，为了保密，删空了
	    	ad.requestAd();
	    	ad.setRefreshInterval(30);
		    ad.setId(1234);
		    ad.setTestMode(false);
		    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
		    		RelativeLayout.LayoutParams.FILL_PARENT, 48
		    		);
		    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        
		bigback.addView(ad, params);
			TextView tv2 = new TextView(this);
	    	tv2.setText("关于应用程序的信息请按Menu键");
	    	tv2.setTextColor(Color.MAGENTA);
	    	RelativeLayout.LayoutParams tvv2 = new RelativeLayout.LayoutParams(
	    			RelativeLayout.LayoutParams.WRAP_CONTENT,
	    			RelativeLayout.LayoutParams.WRAP_CONTENT
	    	);
	    	tvv2.addRule(RelativeLayout.CENTER_HORIZONTAL);
	    	tvv2.addRule(RelativeLayout.ABOVE,1234);
	    bigback.addView(tv2,tvv2);
		    
        this.setContentView(bigback);
        
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("关于本应用程序");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Dialog dlg=new AlertDialog.Builder(Launcher.this)
		.setTitle("桌面图标魔术师v1.0")
		.setMessage("作者：丁然   Email：androidlover@qq.com")
		.setPositiveButton("确定", 
				new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dlg, int which) {
				//升级完成，关闭对话框
				dlg.cancel();
//				dlg.dismiss();
				 
			}
		}
		)
		.create();
		
		dlg.show();	
		
		



		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ad.requestAd();
	}
    
    
}