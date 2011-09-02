import groovy.xml.StreamingMarkupBuilder

//def doc = new XmlSlurper().parse(new File(args[0]))
//def doc = new XmlSlurper().parse(new File("/Users/sassembla/Desktop/svgs/autumn2.svg"))//ストリーミングで処理する
def doc = new XmlParser().parse(new File("/Users/sassembla/Desktop/svgs/autumn2.svg"))//XMLParseを行う場合

def i = 0;
//doc.svg.g.path.each {
//    it.@id= "shape" + (i++)
//}


//ここでくっつけると先頭に来ない。
//doc.appendNode('xml', null)
//doc.xml.@version = "1.1"


//邪魔なdescを消す
doc.remove(doc.description)


//shapeをユニークな名前にする
doc.g.path.each {
    it.@id = "shape" + (i++)
}


//文字列化して出力
//new File ('output.xml').withWriter{writer ->
//    writer << 
//    new StreamingMarkupBuilder().bind{
//        mkp.declareNamespace("":  "http://java.sun.com/xml/ns/j2ee")
//        mkp.yield(doc)
//    }
//}


//XMLParserでそのまま出力
new File("output.xml").withPrintWriter("UTF-8") {writer ->
    writer << '<?xml version="1.1" encoding="UTF-8" standalone="no"?>' << "\n" //ここで書けば楽に先頭に足せる、、うーん。
    
    def printer = new XmlNodePrinter(writer)
    
    printer.preserveWhitespace = true
    printer.print(doc)
}


//ストリーミングで出力、、なのだが、tag0:が鬱陶しい
//def out = new OutputStreamWriter(new FileOutputStream("output.xml"),"UTF-8")
//def outputBuilder = new StreamingMarkupBuilder()
//def writer = outputBuilder.bind() {
//    mkp.xmlDeclaration()
//    mkp.yield(doc)
//}

//out << writer


