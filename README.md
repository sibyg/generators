# Generators
random generators for common types

Every Generator needs to implement: 

```
public interface Generator<T> {
    Random RANDOM = new Random();
    T next();
}
```

## com.siby.generators.Random
Provides static apis to all common type generators
Example,
```
Random.integer.next();
Random.emailAddress.next();
Random.postCode.next();
Random.stringFromRegex(regex).next();
Random.jsonFromSchema(schemaLocation).next()
```

## com.siby.generators.JsonFromSchemaGenerator

It can generate random json objects from a valid json schema location
 
 

## com.siby.utils.TypeCheck ##

A Utility class for type-based testing, where a type is a high-level 
specification of behavior that should hold for a range of data point

Usage:
```
typeCheck(RandomGenerator.STRING, s -> s.length() == s.toCharArray().length).withPreCondition(s -> s.length() < 5).verify(times(5));
```
