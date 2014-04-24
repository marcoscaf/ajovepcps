package br.com.ajovepcps.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtil {
	private Object object;

	public ReflectionUtil(Object obj) {
		this.object = obj;
	}

	public Field[] getAllFields() {
		return this.object.getClass().getDeclaredFields();
	}

	public Map<Object, Object> getFilledAttributes() {
		Map<Object, Object> fieldsAndValues = new HashMap<Object, Object>();

		Field[] fields = getAllFields();

		for (Field field : fields) {

			Object value = this.getValue(field);
			if (value != null && !value.equals(""))
				fieldsAndValues.put(field.getName(), value);
		}

		return fieldsAndValues;

	}

	public Method[] getAllMethods() {
		return this.object.getClass().getDeclaredMethods();
	}

	private Field getFieldByName(String fieldName) {
		Field field = null;
		try {
			field = this.object.getClass().getDeclaredField(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return field;
	}

	private Method getMethodByName(String methodName,
			Class<?>... parameterTypes) {
		Method method = null;
		try {
			method = this.object.getClass().getDeclaredMethod(methodName,
					parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}

	public Object getValue(String fieldName) {
		return getValue(getFieldByName(fieldName));
	}

	public Object getValue(Field field) {
		try {
			if (field != null && this.object != null) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				return field.get(this.object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setValue(String fieldName, Object value) {
		setValue(getFieldByName(fieldName), value);
	}

	public void setValue(Field field, Object value) {
		try {
			if (field != null && this.object != null) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				field.set(this.object, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object invokeMethodByName(String methodName, Object... params) {
		int paramsSize = params.length;
		Class<?>[] paramsTypes = new Class<?>[paramsSize];
		for (int idx = 0; idx < paramsSize; idx++) {
			paramsTypes[idx] = params[idx].getClass();
		}
		return invokeMethod(getMethodByName(methodName, paramsTypes), params);
	}

	public Object invokeMethod(Method method, Object... params) {
		try {
			if (method != null && this.object != null) {
				if (!method.isAccessible()) {
					method.setAccessible(true);
				}
				return method.invoke(this.object, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}