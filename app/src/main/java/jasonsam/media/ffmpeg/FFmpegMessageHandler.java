/**   
 * <p><h1>Copyright:</h1><strong><a href="http://www.smart-f.cn">
 * BeiJing Smart Future Technology Co.Ltd. 2015 (c)</a></strong></p>
 */
package jasonsam.media.ffmpeg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

/**  
 * <p><h1>Copyright:</h1><strong><a href="http://www.smart-f.cn">
 * BeiJing Smart Future Technology Co.Ltd. 2015 (c)</a></strong></p> 
 *
 * <p>
 * <h1>Reviewer:</h1> 
 * <a href="mailto:jiangjunjie@smart-f.cn">jjj</a>
 * </p>
 * 
 * <p>
 * <h1>History Trace:</h1>
 * <li> 2017年5月25日 下午8:44:54    V1.0.0          jjj         first release</li>
 * </p> 
 * @Title FFmpegMessageHandler.java 
 * @Description please add description for the class 
 * @author jjj
 * @email <a href="jiangjunjie@smart-f.cn">jiangjunjie@smart-f.cn</a>
 * @date 2017年5月25日 下午8:44:54 
 * @version V1.0   
 */
public class FFmpegMessageHandler extends Thread {

    private static final String  TAG                  = FFmpegMessageHandler.class.getSimpleName();
    private BufferedReader br = null;
    private int id;
  
    public FFmpegMessageHandler(InputStream is, int id) {
        br = new BufferedReader(new InputStreamReader(is));
        this.id = id;
    }

    /*@Override  
    public void destroy() {
        setStatus(false);
    }*/

    /*public void setAlive(boolean alive) {
        this.alive = alive;
    }*/

    @Override  
    public void run() {
        try {
            Log.i(TAG, "run: "+id);
            String line = null;
            while (/*alive && */(line = br.readLine()) != null) {
                Log.i(TAG, "run, line:" + line);
            }
        } catch (IOException e) {
            Log.i(TAG, "run，e:" + e + ", id:"+this.getId());
            //setAlive(false);
        } finally {
            /*if (this.isAlive()) {
                setAlive(false);
            }*/
        }
    }

}
