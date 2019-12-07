package org.hibernate.envers.bugs.hhh13760;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
//@TypeDef(name = "jsonObject", defaultForType = JSONObject.class, typeClass = JSONConverter.ForObject.class)
//@TypeDef(name = "jsonArray", defaultForType = JSONArray.class, typeClass = JSONConverter.ForArray.class)
//@TypeDef(name = "string-array", typeClass = StringArrayType.class)
//@TypeDef(name = "enum-string-array", typeClass = EnumArrayType.class, parameters = {
//		@Parameter(name = EnumArrayType.SQL_ARRAY_TYPE, value = "text") })
// @TypeDef(name = "range", typeClass = PostgreSQLRangeType.class, defaultForType = Range.class)
@Access(AccessType.FIELD)
public abstract class Base {

}
