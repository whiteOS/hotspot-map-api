package com.entire.hotspotmap.system.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MathUtil {

	public static BigDecimal c_str(String v) {
		if (null == v || "".equals(v)) {
			return new BigDecimal(0);
		}
		return new BigDecimal(v);
	}

	public static BigDecimal c_int(Integer v) {
		if (null == v) {
			return new BigDecimal(0);
		}
		return new BigDecimal(v);
	}

	//除法
	public static BigDecimal divide(BigDecimal a, BigDecimal b, Integer dec_rhu) {
		if (a == null || b == null || a.compareTo(BigDecimal.ZERO) == 0 || b.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		} else {
			return a.divide(b, dec_rhu, BigDecimal.ROUND_HALF_UP);
		}
	}

	//乘法
	public static BigDecimal multiply(BigDecimal a, BigDecimal b, Integer dec_rhu) {
		if (a == null || b == null || a.compareTo(BigDecimal.ZERO) == 0 || b.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		} else {
			return a.multiply(b).setScale(dec_rhu, BigDecimal.ROUND_HALF_UP);
		}
	}

	//加法
	public static BigDecimal add(BigDecimal a, BigDecimal b, Integer dec_rhu) {
		if (a != null && b != null) {
			return a.add(b).setScale(dec_rhu, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}

	//减法
	public static String add(String a, String b) {
		if (a != null && b != null) {
			BigDecimal _a = new BigDecimal(a);
			BigDecimal _b = new BigDecimal(b);
			return _a.add(_b).toString();
		}
		return null;
	}

	//减法
	public static BigDecimal subtract(BigDecimal a, BigDecimal b, Integer dec_rhu) {
		if (a != null && b != null) {
			return a.subtract(b).setScale(dec_rhu, BigDecimal.ROUND_HALF_UP);
		}
		return null;
	}

	//减法
	public static String subtract(String a, String b) {
		if (a != null && b != null) {
			BigDecimal _a = new BigDecimal(a);
			BigDecimal _b = new BigDecimal(b);
			return _a.subtract(_b).toString();
		}
		return null;
	}

	public static String scale(Double v) {
		return scale(v, 2);
	}

	public static String scale(Double v, Integer p) {
		return new BigDecimal(v).setScale(p, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static String fmt(Object v, Integer digits) {
		if (v == null) {
			return "";
		}
		digits = (digits == null ? 2 : digits);
		DecimalFormat nf = new DecimalFormat("0");
		nf.setMaximumFractionDigits(digits);
		return nf.format(v);
	}

	public static String fmt2(Object v) {
		if (v == null) {
			return "0";
		}
		DecimalFormat df1 = new DecimalFormat("0.00");
		return df1.format(v);
	}


	/**
	 * 数字 万为单位,并且去除小数点
	 *
	 * @return
	 */
	public static String costtt(Object v) {
		if (v == null) {
			return "0";
		}
		Double d = Double.parseDouble(v.toString());
		if (d >= 100000 || d <= -10000) {
			d = d / 10000;
			return new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP).toString() + "万";
		} else if ((d >= 10000 && d < 100000) || d > -100000 && d <= -10000) {
			d = d / 10000;
			return new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP).toString() + "万";
		} else {
			return scale(d, 0) + "元";
		}
	}


	/**
	 * 数字 万为单位,并且去除小数点
	 *
	 * @return
	 */
	public static String costttt(Object v) {
		if (v == null) {
			return "0";
		}
		Double d = Double.parseDouble(v.toString());
		if (d - 0 != 0) {
			if (d > 10000 || d < -10000) {
				d = d / 10000;
				return new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP) + "";
			} else {
				d = d / 10000;
				return new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP) + "";
			}
		} else {
			return "0";
		}
	}

	/**
	 * 两个数字计算百分比, (a/b)*100 + '%'
	 *
	 * @return
	 */
	public static String per(Object a, Object b) {
		if (a == null || b == null) {
			return "0%";
		}
		BigDecimal _a = new BigDecimal(a.toString());
		BigDecimal _b = new BigDecimal(b.toString());
		if (_a.intValue() - 0 == 0 || _b.intValue() - 0 == 0) {
			return "0%";
		}
		BigDecimal r = _a.divide(_b, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));

		return r.intValue() + "%";
	}

	/**
	 * 两个数字计算百分比, (a-b)/b*100 + '%'
	 *
	 * @return
	 */
	public static String tb(Object a, Object b) {
		if (a == null || b == null) {
			return "0%";
		}
		BigDecimal _a = new BigDecimal(a.toString());
		BigDecimal _b = new BigDecimal(b.toString());
		if (_a.intValue() - 0 == 0 || _b.intValue() - 0 == 0) {
			return "0%";
		}
		BigDecimal r = _a.subtract(_b).divide(_b.abs(), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
		return (r.intValue() > 0 ? ("+" + r.intValue() + "%") : r.intValue() + "%");
	}

}
