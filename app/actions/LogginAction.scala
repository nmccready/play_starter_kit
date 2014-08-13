package actions

import scala.concurrent._
import play.api.mvc._
import play.api.Logger

case class Logging[A](action: Action[A], actionName: Option[String] = Some("Calling action")) extends Action[A] {

  def apply(request: Request[A]): Future[Result] = {
    actionName.map(Logger.info(_))
    action(request)
  }

  lazy val parser = action.parser
}
