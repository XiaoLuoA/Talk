package convert;

import java.util.Map;


import ognl.DefaultTypeConverter;
import service.Color;

public class ColorConvert extends DefaultTypeConverter {
	
	public Object convertValue(Map context
			, Object value, Class toType)
		{
			System.out.println("come in convert!!!");
			// 当需要将字符串向Color类型转换时
			if (toType == Color.class )
			{
				Color color = new Color();
				String ret ="#";
				String[] params = ((String[])value)[0].split(",");
				for(int i=0;i<params.length;i++){
					ret+= Integer.toHexString(Integer.parseInt(params[i]));
				}
				color.setName(ret);
				return color;
			}
			else if (toType == String.class )
			{
				String s = ((Color)value).getName();
				String ret="";
				for(int i =0;i<s.length();i+=2){
					ret+=Integer.parseInt(s.substring(i,i+2),16)+",";
				}
				return ret;
			}
			return null ;
		}
	
}
