package com.alibaba.fastjson.parser.deserializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;

public class ArrayListTypeFieldDeserializer extends FieldDeserializer {

	private final Type itemType;
	private int itemFastMatchToken;
	private ObjectDeserializer deserializer;

	public ArrayListTypeFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
		super(clazz, fieldInfo);

		Type fieldType = getFieldType();
		if (fieldType instanceof ParameterizedType) {
			this.itemType = ((ParameterizedType) getFieldType()).getActualTypeArguments()[0];
		} else {
			this.itemType = Object.class;
		}
	}

	public int getFastMatchToken() {
		if (isImplicit()) {
			return JSONToken.LITERAL_STRING;
		} else {
			return JSONToken.LBRACKET;
		}

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
		if (parser.getLexer().token() == JSONToken.NULL) {
			setValue(object, null);
			return;
		}

		ArrayList list = new ArrayList();

		ParseContext context = parser.getContext();

		parser.setContext(context, object, fieldInfo.getName());
		parseArray(parser, objectType, list);
		parser.setContext(context);

		if (object == null) {
			fieldValues.put(fieldInfo.getName(), list);
		} else {
			setValue(object, list);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final void parseArray(DefaultJSONParser parser, Type objectType, Collection array) {
		Type itemType = this.itemType;
		ObjectDeserializer itemTypeDeser = this.deserializer;

		if (itemType instanceof TypeVariable //
				&& objectType instanceof ParameterizedType) {
			TypeVariable typeVar = (TypeVariable) itemType;
			ParameterizedType paramType = (ParameterizedType) objectType;

			Class<?> objectClass = null;
			if (paramType.getRawType() instanceof Class) {
				objectClass = (Class<?>) paramType.getRawType();
			}

			int paramIndex = -1;
			if (objectClass != null) {
				for (int i = 0, size = objectClass.getTypeParameters().length; i < size; ++i) {
					TypeVariable item = objectClass.getTypeParameters()[i];
					if (item.getName().equals(typeVar.getName())) {
						paramIndex = i;
						break;
					}
				}
			}

			if (paramIndex != -1) {
				itemType = paramType.getActualTypeArguments()[paramIndex];
				if (!itemType.equals(this.itemType)) {
					itemTypeDeser = parser.getConfig().getDeserializer(itemType);
				}
			}
		}

		final JSONLexer lexer = parser.getLexer();

		if (!isImplicit()) {
			if (lexer.token() != JSONToken.LBRACKET) {
				String errorMessage = "exepct '[', but " + JSONToken.name(lexer.token());
				if (objectType != null) {
					errorMessage += ", type : " + objectType;
				}
				throw new JSONException(errorMessage);
			}
		}

		if (itemTypeDeser == null) {
			itemTypeDeser = deserializer = parser.getConfig().getDeserializer(itemType);
			itemFastMatchToken = deserializer.getFastMatchToken();
		}

		lexer.nextToken(itemFastMatchToken);
		System.out.println("current token:" + lexer.tokenName());
		System.out.println("next char:" + lexer.getChar());

		for (int i = 0;i<2; ++i) {
			if (lexer.isEnabled(Feature.AllowArbitraryCommas)) {
				while (lexer.token() == JSONToken.COMMA) {
					lexer.nextToken();
					continue;
				}
			}

			if (lexer.token() == JSONToken.RBRACKET) {
				break;
			}

			Object val = itemTypeDeser.deserialze(parser, this, itemType, i);
			array.add(val);
			if (i == 1) {
				break;
			}
			// TODO:调过item name
			System.out.println("item name:" + getName());
			System.out.println("current token:" + lexer.tokenName());
			System.out.println("next char:" + lexer.getChar());
			if (isImplicit()) {
				String key = lexer.scanSymbol(parser.getSymbolTable());
				lexer.nextToken();
				lexer.nextToken();
			}
			System.out.println("current token:" + lexer.tokenName());
			System.out.println("next char:" + lexer.getChar());

			parser.checkListResolve(array);

			if (lexer.token() == JSONToken.COMMA) {
				lexer.nextToken(itemFastMatchToken);
				continue;
			}
		}

		System.out.println("current token:" + lexer.tokenName());
		System.out.println("next char:" + lexer.getChar());
		if (!isImplicit()) {
			lexer.nextToken(JSONToken.COMMA);
		}
		//current token:,
		//next char:
		System.out.println("current token:" + lexer.tokenName());
		System.out.println("next char:" + lexer.getChar());
	}

}
