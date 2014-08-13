package controllers

import play.api._
import play.api.mvc._
import play_starter_kit.{BuildInfo => B}

trait Application extends Controller {

  def index = Action {
    Ok(views.html.index("Hello from play!"))
  }

  def version = Action {
    Ok(s"${B.name} : ${B.version} : ${B.organization}")
  }
  def shutDown() = Action {
    DefaultGlobal.onStop(play.api.Play.current)
    Ok("GoodBye!")
  }
}

object Application extends Application
