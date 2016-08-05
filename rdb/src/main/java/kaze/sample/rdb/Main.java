package kaze.sample.rdb;

import kaze.App;

public class Main {
  public static void main(String[] args) {
    Rdb.init();
    App.start("kaze.sample.rdb.http");
  }
}

