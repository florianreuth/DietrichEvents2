# DietrichEvents2

One of the fastest Java event systems in the world using compiler optimizations, which still has a lot of features

## Use in Gradle

If you want to depend on DietrichEvents2 in your own project, use the Maven repository here:

https://mvnrepository.com/artifact/de.florianreuth/dietrichevents2

or

https://maven.florianreuth.de/#/snapshots/de/florianreuth/dietrichevents2 (for snapshots)

The repository page includes the latest coordinates and setup instructions.

Jar builds can be downloaded from my build server: https://build.florianreuth.de/job/DietrichEvents2/

## Example usage

### Create instance

You can use either **new DietrichEvents2 (exception -> {});** or **DietrichEvents2.global ()** to access an instance of
the EventSystem,

### Create an Event

```java
public interface ExampleListener {

    void onTest(final String example);

    class ExampleEvent extends AbstractEvent<ExampleListener> {

        /**
         * The ID has to be incremented for every new Event
         */
        public static final int ID = 0;
        public final String example;

        public ExampleEvent(final String example) {
            this.example = example;
        }

        @Override
        public void call(ExampleListener listener) {
            listener.onTest(example);
        }
    }
}
```

### Register Listener

```java
public class Test implements ExampleListener {

    public void begin() {
        DietrichEvents2.global().subscribe(ExampleEvent.ID, this);
    }

    @Override
    public void onTest(String example) {
        System.out.println("Executed once!");
        DietrichEvents2.global().unsubscribe(ExampleEvent.ID, this);
    }
}
```

### Calling an Event

````java
// There are multiple call methods which can be used depending on the situation:

// - callUnsafe() -> Calls the event without any sanity

// - callExceptionally() -> Calls the event without error handling

// - call() -> Calls the event with global error handling

// - callBreakable() -> Calls the event with error handling for every listener and supports
// BreakableException to be thrown (also does resize)

DietrichEvents2.global().call(ExampleEvent.ID, new ExampleEvent("Hello World!"));
````

## JMH Benchmark

The Benchmark shows the average time it takes to call an event 100.000 times. All Benchmarks are run with the same code
(see **src/jmh/java**), but different Java versions. If an event system does not appear in every list, it does not exist
for the particular Java version, or would not work without modifying it. <br>

### Hardware specification

- CPU: Intel (R) Core (TM) i9-10900K CPU @ 3.70GHz
- RAM: 48,0GB DDR4
- GPU: NVIDIA GeForce RTX 3070 Ti
- OS: Windows 11 Home 22H2 (Build 22621.1992)

### Java 17

| Benchmark                                                                     | Mode | Cnt | Score        | Error      | Units |
|-------------------------------------------------------------------------------|------|-----|--------------|------------|-------|
| [DietrichEvents2](https://github.com/florianreuth/DietrichEvents2)            | avgt | 4   | 310318,125   | 124933,800 | ns/op |
| [ASMEvents](https://github.com/Lenni0451/ASMEvents)                           | avgt | 4   | 546376,840   | 18561,729  | ns/op |
| [ChimeraEventBus](https://github.com/FelixH2012/ChimeraEventBus)              | avgt | 4   | 581672,678   | 71045,952  | ns/op |
| [norbit](https://github.com/CrosbyDev/norbit)                                 | avgt | 4   | 604412,122   | 28715,740  | ns/op |
| [DietrichEvents](https://github.com/florianreuth/DietrichEvents)              | avgt | 4   | 627457,818   | 12842,704  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (MinimalEventManager)       | avgt | 4   | 769492,673   | 18975,650  | ns/op |
| [DarkMagician6](https://bitbucket.org/DarkMagician6/eventapi/src/master/)     | avgt | 4   | 1020463,350  | 70117,174  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (LambdaMetaFactory) | avgt | 4   | 1134071,045  | 41718,145  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (Method Handles)    | avgt | 4   | 1593772,392  | 66639,866  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (Reflection)        | avgt | 4   | 2164632,230  | 49576,755  | ns/op |
| [Cydhra](https://github.com/Cydhra/EventSystem/tree/master) (Event System)    | avgt | 4   | 5169086,080  | 58597,729  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (EventManager)              | avgt | 4   | 6735240,280  | 221306,805 | ns/op |
| [Guava](https://github.com/google/guava)                                      | avgt | 4   | 15337145,465 | 247949,530 | ns/op |

### Java 11

| Benchmark                                                                     | Mode | Cnt | Score        | Error      | Units |
|-------------------------------------------------------------------------------|------|-----|--------------|------------|-------|
| [DietrichEvents2](https://github.com/florianreuth/DietrichEvents2)            | avgt | 4   | 415349,165   | 7314,048   | ns/op |
| [ASMEvents](https://github.com/Lenni0451/ASMEvents)                           | avgt | 4   | 663676,846   | 16997,570  | ns/op |
| [ChimeraEventBus](https://github.com/FelixH2012/ChimeraEventBus)              | avgt | 4   | 710557,890   | 72090,826  | ns/op |
| [DietrichEvents](https://github.com/florianreuth/DietrichEvents)              | avgt | 4   | 743307,467   | 18786,064  | ns/op |
| [DarkMagician6](https://bitbucket.org/DarkMagician6/eventapi/src/master/)     | avgt | 4   | 753029,999   | 16373,710  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (MinimalEventManager)       | avgt | 4   | 770001,532   | 24463,218  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (ASMEventManager)           | avgt | 4   | 771670,427   | 13801,284  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (LambdaMetaFactory) | avgt | 4   | 1130460,687  | 30560,981  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (Method Handles)    | avgt | 4   | 1504976,295  | 69021,509  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (Reflection)        | avgt | 4   | 2205444,424  | 337975,180 | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (InjectionEventManager)     | avgt | 4   | 3015519,829  | 44426,039  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (EventManager)              | avgt | 4   | 5504772,250  | 46947,848  | ns/op |
| [Cydhra](https://github.com/Cydhra/EventSystem/tree/master) (Event System)    | avgt | 4   | 5794477,294  | 113790,263 | ns/op |
| [Guava](https://github.com/google/guava)                                      | avgt | 4   | 11656575,419 | 533926,166 | ns/op |

### Java 8

| Benchmark                                                                     | Mode | Cnt | Score        | Error      | Units |
|-------------------------------------------------------------------------------|------|-----|--------------|------------|-------|
| [DietrichEvents2](https://github.com/florianreuth/DietrichEvents2)            | avgt | 4   | 635392,941   | 25647,033  | ns/op |
| [ASMEvents](https://github.com/Lenni0451/ASMEvents)                           | avgt | 4   | 813149,931   | 31763,759  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (LambdaMetaFactory) | avgt | 4   | 1129216,906  | 16383,663  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (MinimalEventManager)       | avgt | 4   | 1327251,543  | 55359,321  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (ASMEventManager)           | avgt | 4   | 1399196,527  | 55170,229  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (Method Handles)    | avgt | 4   | 1495215,777  | 6336,098   | ns/op |
| [DietrichEvents](https://github.com/florianreuth/DietrichEvents)              | avgt | 4   | 1497398,449  | 128637,113 | ns/op |
| [DarkMagician6](https://bitbucket.org/DarkMagician6/eventapi/src/master/)     | avgt | 4   | 1870394,664  | 59848,353  | ns/op |
| [LambdaEvents](https://github.com/Lenni0451/LambdaEvents) (Reflection)        | avgt | 4   | 2169950,957  | 50804,811  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (InjectionEventManager)     | avgt | 4   | 3832627,003  | 73982,528  | ns/op |
| [EventAPI](https://github.com/Lenni0451/EventAPI) (EventManager)              | avgt | 4   | 5963389,296  | 956568,797 | ns/op |
| [Cydhra](https://github.com/Cydhra/EventSystem/tree/master) (Event System)    | avgt | 4   | 7357398,698  | 92774,097  | ns/op |
| [Guava](https://github.com/google/guava)                                      | avgt | 4   | 11945301,707 | 239846,530 | ns/op |

Date of the benchmark: 2023-07-17

## Contact

- Issues: https://github.com/florianreuth/DietrichEvents2/issues
- Discord: https://florianreuth.de/discord
