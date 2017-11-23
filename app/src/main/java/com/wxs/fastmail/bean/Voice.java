package com.wxs.fastmail.bean;

import java.util.List;

/**
 * Created by wxs on 2017/8/31.
 */

public class Voice {
    /**
     * result : 乐，你在干什么？
     * uid : 897098ab9e96455d8be279b40bc3764c
     * confidence : 1
     * finish : 1
     * version : 4.0
     * nbest : [{"result":"乐，你在干什么？","lexical":"乐 你 在 干什么","confidence":1}]
     * status : 1
     */
    private String result;
    private String uid;
    private int confidence;
    private int finish;
    private String version;
    private int status;
    private List<NbestBean> nbest;

    public String getResult()
    {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NbestBean> getNbest() {
        return nbest;
    }

    public void setNbest(List<NbestBean> nbest) {
        this.nbest = nbest;
    }

    public static class NbestBean {
        /**
         * result : 乐，你在干什么？
         * lexical : 乐 你 在 干什么
         * confidence : 1
         */

        private String result;
        private String lexical;
        private int confidence;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getLexical() {
            return lexical;
        }

        public void setLexical(String lexical) {
            this.lexical = lexical;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }
    }
}
