package njust.tools

import java.util

import njust.model.Image
import com.alibaba.fastjson.{JSON, JSONObject}
import scalaj.http.{Http, HttpOptions}
import spray.json._


class Request {
  /**
    *
    * @param id image id
    * @param base server url
    * @return
    */
  def fetchImage(id: Int, base: String): Image = {
    //      var id
    var imageSourceJSON = new JSONObject()
    imageSourceJSON.put("id",id);


    // query image information
    val response = Http(base + "/rest/image/getimage")
      .postForm
      .param("id",id.toString)
      //      .postData(imageSourceJSON.toString())
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    var image = new Image();
    var imageInfo = response.body.parseJson
    var bands = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("bands")(0)
    var rows = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("rows")(0)
    var samples = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("samples")(0)
    var datatype = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("datatype")(0)
    var interleave = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("interleave")(0)
    var hdfsurl = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("hdfsurl")(0)
    var wavelength = imageInfo.asJsObject.getFields("data")(0).asJsObject.getFields("wavelength")(0)
    image.setId(id)
    image.setDatatype((datatype.toString().toByte));
    image.setRows(rows.toString().toShort)
    image.setSamples(samples.toString().toShort)
    image.setBands((bands.toString().toShort))
    image.setInterleave(interleave.toString().replaceAll("\"",""))
    image.setHdfsurl(hdfsurl.toString())
    image.setWavelength(wavelength.toString())

    image;
  }

  /**
    *
    * @param map
    * @param imageid
    * @param base
    */

  def insertAbundances(map: util.HashMap[Integer, Double], imageid: Int, base: String): Unit = {
    var insertJSON = new JSONObject()
//    val mappp = new JSONObject(map)
    insertJSON.put("imageid",imageid);
    insertJSON.put("map", JSON.toJSON(map))

    val response = Http(base + "/rest/image/insertAbundances")
      .postData(insertJSON.toJSONString)
      .header("Content-Type", "application/json")
      .header("Charset", "UTF-8")
      .option(HttpOptions.readTimeout(10000)).asString
    null
  }



}
