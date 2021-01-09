package io.github.jasonheo

import java.net.URL

import com.typesafe.config.ConfigFactory
import pureconfig.{CamelCase, ConfigFieldMapping, ProductHint}

case class Server(hostname: String, port: Int)

case class MyConfig(name: String, server: Server)

object Main {
  def main(args: Array[String]): Unit = {
    readFileInRes()

    readHoconFileInRes()
  }

  private def readFileInRes(): Unit = {
    // 1. 파일 읽기: classpath에 존재하는 파일
    println(scala.io.Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("conf.json")).mkString)

    // 2. 파일 읽기: 절대 경로로 입력
    //    위 방식과 차이점: getClassLoader()가 없다
    println(scala.io.Source.fromInputStream(getClass.getResourceAsStream("/conf.json")).mkString)

    // 3. 파일 경로 출력하기
    val res = getClass.getResource("/conf.json")
    println(res.getPath)
  }

  private def readHoconFileInRes(): Unit = {
    val configUrl: URL = getClass.getClassLoader.getResource("app1.conf")

    val myConfig: MyConfig = getConfigFromURL(configUrl)

    println(myConfig)
  }

  private def getConfigFromURL(config: URL): MyConfig = {
    implicit def hint[T] = ProductHint[T](ConfigFieldMapping(CamelCase, CamelCase))

    pureconfig.loadConfigOrThrow[MyConfig](ConfigFactory.parseURL(config).resolve)
  }
}

