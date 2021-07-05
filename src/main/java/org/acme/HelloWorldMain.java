package org.acme;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class HelloWorldMain implements QuarkusApplication {

  @Override
  public int run(String... args) throws Exception {
    System.out.println("Hello World");
    Quarkus.waitForExit();
    return 10;
 }
}