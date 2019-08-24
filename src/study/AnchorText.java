package study;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouxinghang
 * @date 2019-08-22
 */
public class AnchorText {

    //文章中出现相同锚文本，仅首次替换
    private static final boolean hit_once = false;

    /*
     * 锚文本map
     * key 锚文本
     * value 锚文本类型
     */
    private static final Map<String, String> full_map = new HashMap<>();

    //锚文本首字母 提升效率
    private static final Set<Character> initial_set = new HashSet<>();

    //锚文本最长值
    private static Map<Character, Integer> max_length_map = new HashMap<>();

    private static final String  REPLACESTR = "$HXZQ$%s$HXZQ$";

    private static final String  FINDKEY = "\\$HXZQ\\$(.*?)\\$HXZQ\\$";
    //超链接替换
    private static final String A_HREF="<a href=\"%s\" >%s</a>";


    public static String replaceKey(String content){
        //hash扫描
        return   hashScan(content);
    }

    //简单替换扫描
//    private static String replaceScan(String content) {
//        for(Map.Entry<String, String> entry : full_map.entrySet()) {
//            content = content.replace(entry.getKey(), convert(entry.getValue(), entry.getKey()));
//        }
//        return content;
//    }
    //hash扫描
    private static String hashScan(String content) {
        Set<String> temp = new HashSet<>();
        //String to char array
        char[] array = content.toCharArray();
        for(int i=0; i<array.length; i++) {
            //首字母不命中直接跳过
            if(!initial_set.contains(array[i])) {
                continue;
            }

            int max_length = max_length_map.get(array[i]);

            for(int j=1; j<=max_length; j++) {
//                System.out.println("i:" + i +"j:" + j);
                //越界跳过
                if(i + j > array.length) {
                    continue;
                }
                String word = new String(array, i, j);
                //hash匹配
                String type = full_map.get(word);
                if(type != null && (!hit_once || hit_once && !temp.contains(word))) {
                    String anchorText = convert(type, word);
                    array = (new String(array, 0, i) + anchorText + new String(array, i + j, array.length-i-j)).toCharArray();
                    temp.add(word);
                    i += anchorText.length() - 1;
                }
            }
        }
//        System.out.println(new String(array));
        return new String(array);
    }

    /**
     * 替换关键字
     * @param type
     * @param word
     * @return
     */
    private static String convert(String type, String word) {
        return String.format(REPLACESTR,word);
    }

    /**
     * 初始化字典
     * @param paramMap
     */
    public static void init(Map<String,Object> paramMap) {
        if(paramMap!=null){
            for(Map.Entry<String, Object> entry : paramMap.entrySet()) {
                char initial = entry.getKey().charAt(0);
                full_map.put(entry.getKey(), String.valueOf(entry.getValue()));
                initial_set.add(entry.getKey().charAt(0));
                if(max_length_map.get(initial) == null || entry.getKey().length() > max_length_map.get(initial)) {
                    max_length_map.put(initial, entry.getKey().length());
                }
            }
        }

    }


//    private static void init() throws IOException {
//        FluentIterable<File> iterable = Files
//            .fileTreeTraverser().breadthFirstTraversal(new File(dic_path)); for(File file : iterable) { if(file.getName().endsWith(".dic")) { for(String word : FileUtils.readLines(file, "UTF-8")) { char initial = word.charAt(0); full_map.put(word, file.getName().replace(".dic", "")); initial_set.add(word.charAt(0)); if(max_length_map.get(initial) == null || word.length() > max_length_map.get(initial)) { max_length_map.put(initial, word.length()); } } } } }*/ /**  * 文本替换URL  * @param body * @param url 替换的url  * @param secParamMap 股票信息  * @return */
//
//            public static String replaceUrlToBody(String body,String url,Map<String,Object> secParamMap){ Pattern p = Pattern.compile(FINDKEY); Matcher m = p.matcher(body); while (m.find()) { body = body.replace(m.group(), convertToUrl(m.group(1), url,secParamMap)); } return body; } //简单替换扫描 private static String convertToUrl(String replaceKey,String url,Map<String,Object> secParamMap) { String secMsg = String.valueOf(secParamMap.get(replaceKey)); String [] secParms = new String[3]; String newurl =""; if(!StringUtils.isEmpty(url)){ if(url.startsWith("javascript") || url.startsWith("JAVASCRIPT")){ if(!StringUtils.isEmpty(secMsg) && !"null".equals(secMsg)) { secParms = secMsg.split("_"); newurl = String.format(url, secParms[1], secParms[0], secParms[2]); } }else{ if(!StringUtils.isEmpty(secMsg) && !"null".equals(secMsg)){ secParms=secMsg.split("_"); Map<String,Object> urlParamMap = new HashMap<String,Object>(); urlParamMap.put("secuMarket",secParms[0]); urlParamMap.put("secuCode",secParms[1]); urlParamMap.put("secuAbbr",secParms[2]); newurl = UrlUtils.setUrlParamValue(url,urlParamMap); } } } String ahref = String.format(A_HREF,newurl,replaceKey); return ahref; } public static final Pattern PATTERN = Pattern.compile("<img\\s+(?:[^>]*)src\\s*=\\s*([^>]+)/>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE); /**  * 将报文中的img的src 进行替换  * @param body * @return String  */ public static String replaceImgSrcUrlToBody(String body,String url) { Matcher matcher = PATTERN.matcher(body); while(matcher.find()){ String group= matcher.group(1); if (group == null){ continue; } //这里可能还需要更复杂的判断,用以处理src="...."内的一些转义符 if(group.startsWith("'")){ body = body.replace(group.substring(1,group.indexOf("'", 1)), convertImgSrcUrl(group.substring(1,group.indexOf("'", 1)),url)); }else if(group.startsWith("\"")){ body = body.replace(group.substring(1,group.indexOf("\"", 1)), convertImgSrcUrl(group.substring(1,group.indexOf("\"", 1)),url)); }else{ body = body.replace(group.split("\\s")[0], convertImgSrcUrl(group.split("\\s")[0],url)); } } return body; } private static String convertImgSrcUrl(String replaceKey,String url) { if(replaceKey.startsWith("\\")){ replaceKey = replaceKey.replace("\\\"","\""); } if(replaceKey.endsWith("\\\"")){ replaceKey = replaceKey.replace("\\\"","\""); } replaceKey= replaceKey.replace("/upload",url+"/upload"); return replaceKey; } public static void main(String[] args) { long begin =System.currentTimeMillis(); String str ="<p>　　$HXZQ$中兴通讯$HXZQ$官网22日消息，公司正式发布智能停车系统NB-IoT(窄带物联网)版本，" + "包括物联网数据检测单元(IDU)、物联网运营管理平台(IOP)及配套手机APP。" + "<img src=\\\"/upload/topic/2016-01-06/1452037131450087841.jpg\\\" title=\\\"1452037131450087841.jpg\\\" alt=\\\"科华恒盛.jpg\\\"/>该系统基于全新的物联网技术NB-IoT，接入能力提升50倍以上，并能实现系统的稳定接入和数据可靠传输。" + "</p><p><br/></p>" + "<p>　　据了解，该系统切合道路停车和露天停车场景需求，将有效解决道路停车收费管理难、园区内停车、找车位难等问题，" + "推动城市停车智能化、科学化发展。在部署该系统后，停车拥堵率将降低12%，寻找车位时间减少43%，停车管理收入提高30%。目前该系统已在深圳、南京等地试商用，后续将拓展到更多城市。</p><p><br/></p>" + "<p>　　相比传统的蓝牙、WiFi等物联网技术，NB-IoT技术具有大容量、广覆盖、低功耗、低成本和高稳定性等优势，适合长距离、低速率、低功耗、多终端物联网业务的通信，在物联网技术中先发优势明显。机构预计，" + "通过NB-IoT技术进行的物联网终端覆盖，未来占比将达到25%。</p><p><br/></p><p>　　自去年下半年3GPPNB-IoT核心标准发布后，三大运营商作为该标准的重要推动力，今年以来部署进程加快。" + "近日中国电信正式发布“中国电信NB-IoT企业标准(V1.0)”，并宣布启动NB-IoT大规模外场试验，预计到今年6月中国电信将建成全球规模最大、覆盖及应用最广泛的NB-IoT网络。" + "中国移动此前提出的“大连接”战略将大力推动NB-IoT标准成熟和国际化发展，着力打造领先的物联网基础设施和创新生态，推动物联网实现全球连接。</p><p><br/></p><p>　　目前中国移动正在积极布局NB-IoT业务领域。" + "1月底江西移动已顺利完成覆盖鹰潭市全域的NB-IoT网络的建设及开通，这标志着全国第一张地市级全域覆盖的NB-IoT网络顺利建成。近日江苏移动在南京也启动了NB-IoT正式商用，" + "应用领域包括$HXZQ$智慧农业$HXZQ$、智能抄表等。$HXZQ$中国联通$HXZQ$已经开始启动大规模NB-IoT外场实验计划，为商用化做准备。" + "另外，华为等设备制造商也在积极布局，已面向全球发布了端到端NB-IoT解决方案，将致力协助运营商利用NB-IoT技术开拓新的市场空间，并与福州市政府合作推动NB-IoT产品研发、业务试点。</p><p><br/></p><p>　　" + "资料显示，全球已经有21家运营商承诺部署NB-IoT网络，有59家公司加入到NB-IoT论坛中，今年底全球将部署25张NB-IoT商用网络。机构预计，在国内运营商和设备制造商的推动下，" + "今年将成为物联网产业培育期和商用元年。未来几年我国物联网行业年均增速将达30%，到2018年市场规模将超过1.5万亿元，NB-IoT相关的芯片传感器、重点行业应用场景和平台型应用等细分行业有望率先受益。</p><p><br/></p><p>　" + "　相关公司受关注：</p><p><br/></p><p>　　$HXZQ$东杰智能$HXZQ$：切入“设备制造+后端运营”的智能立体停车库模式，复制性极强。</p><p><br/></p><p>　　" + "$HXZQ$五洋科技$HXZQ$：旗下伟创自动化拥有的智能停车装备处于行业领先地位。</p><p><br/></p><p>　　" + "$HXZQ$捷顺科技$HXZQ$：已与上海迪斯尼签订有停车场合同。此外公司与苏州一卡通和城市一卡通提供商达成战略合作，开展智慧停车业务发展。</p><p><br/></p><p>　" + "　安居宝：推出了“智慧云停车”系统，利用自身的停车硬件优势和“互联网+”整合了城市的停车资源，以解决“停车难”问题。</p><p><br/></p><p>　" + "　$HXZQ$数字政通$HXZQ$：全资子公司北京$HXZQ$数字政通$HXZQ$智能泊车技术有限公司与湖南昱翰信息科技有限公司拟共同出资在湖南长沙设立“湖南好泊信息技术有限公司”，" + "$HXZQ$数字政通$HXZQ$智能泊车拟出资300万元，<img src=\\\"/upload/topic/2016-01-06/1452037105294037138.jpg\\\" title=\\\"1452037105294037138.jpg\\\" alt=\\\"顺荣三七.jpg\\\"/> 持股30%。</p><p>" + "<img src=\\\"/upload/topic/2016-01-06/1452037122325063181.jpg\\\" title=\\\"1452037122325063181.jpg\\\" alt=\\\"劲嘉股份.jpg\\\"/><br/>" + "<img src=\\\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488337557819&di=b87c6473d4612ac5085deb1c3a570a34&imgtype=0&src=http%3A%2F%2Fwww.571free.com%2Fuploads%2Fallimg%2F160927%2F2000205564-2.jpg\\\"/></p>"; Map<String,Object> map=new HashMap<String,Object>(); map.put("中兴通讯","00_600001_中兴通讯"); map.put("数字政通","01_600002_数字政通"); Pattern p = Pattern.compile(FINDKEY); Matcher m = p.matcher(str); ArrayList<String> strs = new ArrayList<String>(); while (m.find()) { System.out.println(m.group()+" "+m.group(1)); str = str.replace(m.group(), convertToUrl(m.group(1), "https://szq.com?secuMarket=%s&secuCode=%s&secuAbbr=%s",map)); } System.out.println(str); System.out.println(System.currentTimeMillis()-begin); //html标签的img 的src 替换 System.out.println(replaceImgSrcUrlToBody(str,"https://h.szq.com/upimage")); }

}
