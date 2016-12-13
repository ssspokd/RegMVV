package xmlviewer

/**
 *
 * @author spok
 */
class getXmlAttributeValue {
   
    private String str;
    private String file;
    static  getAttribute(str, file)
    {
        StringBuffer result  = new StringBuffer()
        def langs = new XmlParser().parse(file)
        for(String s : str)
        {
            println s  + " : "  + langs.attribute(s)
            result.append(s + " : " + langs.attribute(s) + "\n")
        }
        return result
    }
    
    static void main(args) 
    {
        def str =  ["Key","DocDate","DocDate","FsspTo","FsspOsp","OrgAMD","DivAMD","Conf","ConfFio","ConfPost"]
        def func =  new getXmlAttributeValue()
        def file = "c:\\131212_02_04507_010_001_003.in"
        def s = func.getAttribute(str,file)
        print(s)
    }
}

