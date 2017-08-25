package playerc.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import junit.framework.TestCase;

/**
 * Author: 		Sergei Golitsinski
 * Created: 	Mar 25, 2006 4:29:40 PM
 */
public class BaseTestCase extends TestCase
{
	   protected Object invokeMethod(Object target, String methodName, Class[] formalParams, Object[] actParams) 
	   {	   
		   try {
			   Class c = target.getClass();	   
			   Method m = c.getDeclaredMethod(methodName, formalParams);	   
			   m.setAccessible(true);		   
			   return m.invoke(target, actParams);
		   }
		 	catch (SecurityException e) 				{ e.printStackTrace(); } 
			catch (IllegalArgumentException e) 		{ e.printStackTrace(); } 
			catch (NoSuchMethodException e)		{ e.printStackTrace(); } 
			catch (IllegalAccessException e) 		{ e.printStackTrace(); } 
			catch (InvocationTargetException e) 	{ e.printStackTrace(); }
			return null;
	   }
}
