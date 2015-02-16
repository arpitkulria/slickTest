package sbtTestWithSlick

import org.scalatest.BeforeAndAfter
import org.scalatest.fixture.FunSuite
import com.knol.core._
import com.knol.impl._
import org.scalatest.WordSpec


class RepoImplTest extends FunSuite {
  val obj = RepoImpl

  test("test of create") {
    //pending
    val interVal = College("IIIM", "Jaipur", 0)

    val output = obj.insert(interVal)
    
    assert(output===Some(1))
  }

}