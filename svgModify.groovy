import groovy.xml.StreamingMarkupBuilder

//def doc = new XmlSlurper().parse(new File(args[0]))
//def doc = new XmlSlurper().parse(new File("/Users/sassembla/Desktop/svgs/autumn2.svg"))//�X�g���[�~���O�ŏ�������
def doc = new XmlParser().parse(new File("/Users/sassembla/Desktop/svgs/autumn2.svg"))//XMLParse���s���ꍇ

def i = 0;
//doc.svg.g.path.each {
//    it.@id= "shape" + (i++)
//}


//�����ł�������Ɛ擪�ɗ��Ȃ��B
//doc.appendNode('xml', null)
//doc.xml.@version = "1.1"


//�ז���desc������
doc.remove(doc.description)


//shape�����j�[�N�Ȗ��O�ɂ���
doc.g.path.each {
    it.@id = "shape" + (i++)
}


//�����񉻂��ďo��
//new File ('output.xml').withWriter{writer ->
//    writer << 
//    new StreamingMarkupBuilder().bind{
//        mkp.declareNamespace("":  "http://java.sun.com/xml/ns/j2ee")
//        mkp.yield(doc)
//    }
//}


//XMLParser�ł��̂܂܏o��
new File("output.xml").withPrintWriter("UTF-8") {writer ->
    writer << '<?xml version="1.1" encoding="UTF-8" standalone="no"?>' << "\n" //�����ŏ����Ίy�ɐ擪�ɑ�����A�A���[��B
    
    def printer = new XmlNodePrinter(writer)
    
    printer.preserveWhitespace = true
    printer.print(doc)
}


//�X�g���[�~���O�ŏo�́A�A�Ȃ̂����Atag0:���T������
//def out = new OutputStreamWriter(new FileOutputStream("output.xml"),"UTF-8")
//def outputBuilder = new StreamingMarkupBuilder()
//def writer = outputBuilder.bind() {
//    mkp.xmlDeclaration()
//    mkp.yield(doc)
//}

//out << writer


