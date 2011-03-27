/**
 *
 *@author	dingran
 *创建日期	2010-8-2 下午01:48:39
 *
 */
package work.dingran.myshortcut;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class EditShortCut extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		RelativeLayout rr = new RelativeLayout(this);
		rr.setBackgroundResource(R.drawable.wallpapera);
		
		
		this.setContentView(rr);
		
	}

	
}
