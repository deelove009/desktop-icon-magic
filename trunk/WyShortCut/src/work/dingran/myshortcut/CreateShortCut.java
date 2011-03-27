/**
 *
 *@author	dingran
 *创建日期	2010-7-27 上午11:47:25
 *
 */
package work.dingran.myshortcut;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreateShortCut extends Activity {

	private static final String ACTION_INSTALL_SHORTCUT = 
		"com.android.launcher.action.INSTALL_SHORTCUT";
	/**
	 * 是否可以有多个快捷方式的副本
	 */
	static final String EXTRA_SHORTCUT_DUPLICATE = "duplicate";
	
	private static final int REQUEST_PICK_BITMAPPACKAGE = 1;
	
	private static final int BITMAPRESULT = 2;
	
	Bitmap localBitmap;
	
	Dialog dlg;
	
	Button bicon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		loadApps();
        
        this.setContentView(R.layout.layout_animation_1);
        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setAdapter(new AppsAdapter());	
        
        
	}

	private List<ResolveInfo> mApps;

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }
    
    public class AppsAdapter extends BaseAdapter implements OnKeyListener{

		public int getCount() {
			return Math.min(32, mApps.size());
		}

		public Object getItem(int position) {
			return mApps.get(position % mApps.size());
		}

		public long getItemId(int position) {
			return position;
		}
		

	public View getView(int position, View convertView, ViewGroup parent) {
		
			final ResolveInfo info = mApps.get(position % mApps.size());
			final String oldname = info.activityInfo.loadLabel(getPackageManager()).toString();
			 ImageItemView v = new ImageItemView(CreateShortCut.this,
					 oldname, 
					info.activityInfo.loadIcon(getPackageManager()));
			
			 final ImageView i;
	         i = new ImageView(CreateShortCut.this);
			 i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
			 
//			 v.seto
			 
			 v.setOnKeyListener(new OnKeyListener(){

				public boolean onKey(View v, int keyCode, KeyEvent event) {
					Log.e("setOnKeyListener", "onKey"+keyCode);
					return false;
				}
				 
			 });
			 
			 v.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					
					/*Intent intent = new Intent();
					intent.setClass(CreateShortCut.this, EditShortCut.class);
					CreateShortCut.this.startActivity(intent);*/
					
					LayoutInflater factory = LayoutInflater.from(CreateShortCut.this);
					//得到自定义对话框
	                final View DialogView = factory.inflate(R.layout.dialog, null);
	                
	                
//	                EditText et2 = (EditText) DialogView.findViewById(R.id.name);
//	                et2.setText(newname);
	                
	                bicon = (Button)DialogView.findViewById(R.id.bicon);
					bicon.setOnClickListener(new OnClickListener(){

						public void onClick(View v) {
							Log.e("bicon", "lalallala");
							
							Bundle bundle = new Bundle();
							Intent pickIntent = new Intent(Intent.ACTION_PICK_ACTIVITY);
			                pickIntent.putExtra(Intent.EXTRA_INTENT,
			                        new Intent("com.betterandroid.launcher2.icons"));
			                pickIntent.putExtra(Intent.EXTRA_TITLE,
			                        "请选择图标包");
			                pickIntent.putExtras(bundle);
			                
			                /** 在这取消掉这个对话框*/
//			                dlg.cancel();
			                
			                startActivityForResult(pickIntent, REQUEST_PICK_BITMAPPACKAGE);
							
							
						}
						
					});
					
					dlg=new AlertDialog.Builder(CreateShortCut.this)
					.setTitle("您要创建("+oldname+")的快捷方式吗?")
					.setIcon(info.activityInfo.loadIcon(getPackageManager()))
					.setView(DialogView)
					.setPositiveButton("创建", 
							new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dlg, int which) {
							
							
							
							EditText et2 = (EditText) DialogView.findViewById(R.id.etname);
							String newname ="";
							Log.e("newname", newname);
							if(!et2.getText().toString().equals("")){
								
								newname = et2.getText().toString();
								Log.e("newname2", newname);
							}
							
							
							Intent shortcutIntent = new Intent(ACTION_INSTALL_SHORTCUT);  
					        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,  
					        		// 设置快捷方式的名字，可以换成自己更改的名字。
					        		(newname==""?oldname:newname));
//					        		oldname);
					        
					        shortcutIntent.putExtra(EXTRA_SHORTCUT_DUPLICATE, false);  
					        Intent intent2 = new Intent(Intent.ACTION_CREATE_SHORTCUT);
					        intent2.addCategory(Intent.CATEGORY_LAUNCHER);

					        intent2.setComponent(new ComponentName(info.activityInfo.packageName,  
					        		info.activityInfo.name));  
					          
					        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent2);
					        
					        Bitmap bitmap = ((BitmapDrawable) i.getDrawable()).getBitmap();

					        /** 做判断，如果localBitmap是空的就用默认的，否则就用localBitmap*/
					        if(localBitmap==null){
					        	//这个putExtra里面是要有Bitmap类型的图标的：
						        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, 
						        		bitmap);
					        }else{
					        	shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, 
					        			localBitmap);
					        }
					        
					        sendBroadcast(shortcutIntent);
							dlg.cancel();
							
						}
					}
					).setNegativeButton("取消",new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dlg, int which) {
							dlg.cancel();
						}
					}
					)
					.create();
					
					dlg.show();	
					
					
					
					
				}
            	
            });
			
			return v;
		}

	public boolean onKey(View v, int keyCode, KeyEvent event) {
		Log.e("setOnKeyListener", "onKey"+keyCode);
		return false;
	}

	
    	
    }
    
    public class ImageItemView extends RelativeLayout {  
	      
		private TextView text;
		private ImageView image;
		
	        public ImageItemView(Context context, String title, Drawable drawable) {  
	            super(context);  
	            RelativeLayout.LayoutParams rrr = new RelativeLayout.LayoutParams(50,
	    				50);
	           rrr.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	           image = new ImageView(context);  
	           image.setId(001);
	           image.setImageDrawable(drawable); 
	           image.setScaleType(ImageView.ScaleType.FIT_CENTER);
	           // 左，上，右，下  
	           image.setPadding(0, 0, 0, 0);
//	           addView(image, new GridView.LayoutParams(50, 50));  
	           image.setLayoutParams(new GridView.LayoutParams(50, 50));
	           addView(image, rrr);  
	           
	           RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT, 
						RelativeLayout.LayoutParams.WRAP_CONTENT);
			   rl.addRule(RelativeLayout.BELOW, 001);
			   rl.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	           text = new TextView(context);  
	           text.setText(title); 
	           text.setTextColor(Color.BLUE);
	           text.setTextSize(10);
//	           text.setSingleLine();
//	           text.setEllipsize(TextUtils.TruncateAt.MARQUEE);
	           text.setMarqueeRepeatLimit(3);
	           addView(text, rl);  
	       }  
	        
	        public ImageView getImageView(){
	        	return image;
	        }

    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode){
		
		case REQUEST_PICK_BITMAPPACKAGE:
			if(data!=null){
				selectedBitmapPackage(this,data);
			}
			break;
		case BITMAPRESULT:
			if(data!=null){
				getBitMap(this,data);
			}
			break;
		}
		
	}
	private void selectedBitmapPackage(Context context,Intent data){
		
		data.setAction("com.betterandroid.launcher2.icons.PICK_ICON_ACTION");
		this.startActivityForResult(data, BITMAPRESULT);
		
	}
    
	private void getBitMap(Context context,Intent data){
		
		localBitmap = (Bitmap)data.getParcelableExtra("data");
//		Log.e("getBitMap", localBitmap.toString());
		BitmapDrawable d = new BitmapDrawable(localBitmap); 
		bicon.setBackgroundDrawable(d);
	}
    
    
}
