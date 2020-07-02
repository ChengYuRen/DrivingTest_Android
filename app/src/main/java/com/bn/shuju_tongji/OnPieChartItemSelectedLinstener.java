package com.bn.shuju_tongji;

import com.bn.shuju_tongji.PieChartView;

/**
 * 饼状图的块被点击的消息监听
 * 欢迎加入QQ群88130145
 * @author ZGY
 * 2012-12
 * 
 * @version 1.0
 */

public interface OnPieChartItemSelectedLinstener {

	/**
	 * 回调
	 * 
	 * @param view
	 *            当前的PieChartView
	 * @param position
	 *            当前查看的块的id，此id的排列顺序由你传入各个块的大小的值的顺序觉得
	 * @param colorRgb
	 *            当前查看的块的颜色
	 * @param size
	 *            当前查看的块的值
	 * @param rate
	 *            当前查看的块所占的比例， 0 < rate < 1
	 * @param isFreePart
	 *            是否是多余的块；当为true时说明：传入的各个块的大小的和小于传入的总体的值，点击的这部分即为总体的值减去各个块的和的部分。
	 * @param rotateTime
	 *            若开启旋转动画，返回旋转动画所需的时间，单位：毫秒。0为不旋转或未开启旋转动画。 <br/>
	 * <br/>
	 *            使用方法同Button的OnClickListener};
	 * 
	 */
	void onPieChartItemSelected(PieChartView view, int position, String colorRgb, float size, float rate, boolean isFreePart, float rotateTime);
}
