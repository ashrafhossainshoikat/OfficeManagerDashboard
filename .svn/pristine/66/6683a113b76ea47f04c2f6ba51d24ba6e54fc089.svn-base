package services

import java.util.ArrayList
import java.net.URI

import scala.util.Random
import scala.collection.JavaConversions._

//import models.usermanagement.User
import connectplus.models.User
import services.Email.EmailSender


object ScalaUtils {
  
  def domainFromUri(url: String): String = {
    if(url==null || url.isEmpty()) return ""
    
    val uri = new URI(url)
    
    if (uri.getHost().startsWith("www.")) uri.getHost().drop(4)
    else uri.getHost()
  }
  
  
  import java.util.Date
  import java.text.SimpleDateFormat
  def formatDateTime(dt: Date): String = {
    if(dt==null) { return AppConstants.NULL_DATE_STRING }
    
    val sdf = new SimpleDateFormat("d/M/yyyy h:mm:ssa")
    sdf.format(dt)
  }
  
  def formatDate(dt: Date): String = {
    if(dt==null) { return AppConstants.NULL_DATE_STRING }
    
    val sdf = new SimpleDateFormat("d/M/yyyy")
    sdf.format(dt)
  }
  
  def formatTime(dt: Date): String = {
    if(dt==null) { return "" }
    
    val sdf = new SimpleDateFormat("h:mm:ssa")
    sdf.format(dt)
  }
  
  
  /** number format checker */
  def isAllDigits(input: String): Boolean = input forall Character.isDigit
  
  def isNumeric(input: String): Boolean = {
    if(!input.contains("."))
      isAllDigits(input)
    else {
      if(input.last == '.') return false
      
      val parts = input.split(".")
      if(parts.length != 2) return false
      
      isAllDigits(parts(0)) && isAllDigits(parts(1))
    }
  }

  def isInteger(input: String): Boolean = {
    try {
      if(input.forall(_.isDigit)){
        Integer.parseInt(input)
        return true
      }
    } catch {
      case e: NumberFormatException => return false
    }
    return false
  }
  
  def isLong(input: String): Boolean = {
    try {
      java.lang.Long.parseLong(input)
      return true
    } catch {
      case e: NumberFormatException => return false
    }
  }
  
  def isDouble(input: String): Boolean = {
    try {
      java.lang.Double.parseDouble(input)
      return true
    } catch {
      case e: NumberFormatException => return false
    }
  }

  
  def stripSome(input: String): String = {
    val data = input.trim
    val somePrefix = "Some("
    val endBracket = ")"

    if (data.startsWith(somePrefix) && data.endsWith(endBracket)) data.drop(somePrefix.length).dropRight(endBracket.length)
    else data
  }

  def objToMap(obj: AnyRef): Map[String, String] = {
    import reflect.runtime.universe._
    import reflect.runtime.currentMirror

    val r = currentMirror.reflect(obj)

    r.symbol.typeSignature.members.toStream
      .collect { case s: TermSymbol if !s.isMethod => r.reflectField(s) }
      .map(r => r.symbol.name.toString.trim -> r.get.toString)
      .toMap
  }
  
  def mapToString(mapData: Map[AnyRef, AnyRef]): String = {
    mapData.map{case(k, v) => k.toString() + ": " + k.toString()}.mkString("\r\n")
  }

  
  def generateVerifyId(): String = {
    import java.util.UUID
	val randomNumber = UUID.randomUUID().toString()
	val vId: String = (new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + "-" + randomNumber.toString)
    
    vId
  }

}
