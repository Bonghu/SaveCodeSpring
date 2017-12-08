/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackboard;

import KnowledgeSource.KnowledgeSource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author LanAnh
 */
public class Blackboard {

    private File file1;
    private File file2;
    private ListDataModel listData1 = new ListDataModel();
    private ListDataModel listData2 = new ListDataModel();

    private List<ResultDisplay> list_result = new LinkedList<ResultDisplay>();
    private ArrayList<KnowledgeSource> listKS = new ArrayList<KnowledgeSource>();

    public Blackboard(ArrayList<KnowledgeSource> listKS) {
        this.listKS = listKS;
    }

    public void registerKS(KnowledgeSource knowledgeSource) {
        knowledgeSource.setBlackBoard(this);
        listKS.add(knowledgeSource);
    }

    public Blackboard() {

    }

    public Blackboard(File f1, File f2, ArrayList<KnowledgeSource> lstKS) {
        this.file1 = f1;
        this.file2 = f2;
        this.listKS = lstKS;
    }

    public File getFile1() {
        return file1;
    }

    public void setFile1(File file1) {
        this.file1 = file1;
    }

    public File getFile2() {
        return file2;
    }

    public void setFile2(File file2) {
        this.file2 = file2;
    }

    public ListDataModel getListData1() {
        return listData1;
    }

    public void setListData1(ListDataModel listData1) {
        this.listData1 = listData1;
    }

    public ListDataModel getListData2() {
        return listData2;
    }

    public void setListData2(ListDataModel listData2) {
        this.listData2 = listData2;
    }

    public List<ResultDisplay> getList_result() {
        return list_result;
    }

    public void setList_result(List<ResultDisplay> list_result) {
        this.list_result = list_result;
    }

    public List<KnowledgeSource> getLstKS() {
        return listKS;
    }

    public void setListKS(ArrayList<KnowledgeSource> listKS) {
        this.listKS = listKS;
    }

// function
    int subKt;

    public ListDataModel ListFolder(File file, int recursive, ListDataModel list_Data) {
        File[] listFile = file.listFiles();
        if (recursive != 0) {
            subKt = file.getAbsolutePath().length() + 1;
        }
        for (int i = 0; i < listFile.length; i++) {
            Date file_Time = new Date(listFile[i].lastModified());
            if (listFile[i].isFile() == true) {
                DataModel data_Model = new DataModel(listFile[i].getAbsolutePath().substring(subKt), file_Time, "white");
                list_Data.add(data_Model);
            } else if (listFile[i].isDirectory() == true) {
                ListFolder(listFile[i], 0, list_Data);
            }
        }
        return list_Data;
    }
    //

    public DataModel lastTime(ListDataModel list_Data) {
        Date maxTime = new Date(0);
        DataModel maxData_model = new DataModel();
        int i;
        for (i = 0; i < list_Data.listData.size(); i++) {
            DataModel dataModel = (DataModel) list_Data.listData.get(i);
            Date fileTime = dataModel.getTime();
            if (fileTime.compareTo(maxTime) > 0) {
                maxTime = fileTime;
                maxData_model = dataModel;
            }
        }
        return maxData_model;
    }
///// ADD List Result

    public List addListResult(ListDataModel listData1, ListDataModel listData2) {
        list_result = new LinkedList<ResultDisplay>();
        listData1 = listData1.sort(listData1);
        listData2 = listData2.sort(listData2);
        int limit = 0;
        if (listData1.listData.size() > listData2.listData.size()) {
            limit = listData1.listData.size();
        } else {
            limit = listData2.listData.size();
        }
        for (int i = 0; i < limit; i++) {
            ResultDisplay result_Display = new ResultDisplay();
            DataModel data_model1;
            DataModel data_model2;
            DataModel last_datamodel1 = (DataModel) this.lastTime(listData1);
            DataModel last_datamodel2 = (DataModel) this.lastTime(listData2);
            if (last_datamodel1.getTime().compareTo(last_datamodel2.getTime()) == 1) {
                for (int j = 0; j < listData1.listData.size(); j++) {
                    data_model1 = (DataModel) listData1.listData.get(j);
                    if (data_model1.getPath().equals(last_datamodel1.getPath())) {
                        ((DataModel) listData1.listData.get(j)).setColor("red");
                    }
                }
                for (int k = 0; k < listData2.listData.size(); k++) {
                    data_model2 = (DataModel) listData2.listData.get(k);
                    if (data_model2.getPath().equals(last_datamodel2.getPath())) {
                        ((DataModel) listData2.listData.get(k)).setColor("blue");
                    }
                }
            } else if (last_datamodel1.getTime().compareTo(last_datamodel2.getTime()) == -1) {
                for (int m = 0; m < listData1.listData.size(); m++) {
                    data_model1 = (DataModel) listData1.listData.get(m);
                    if (data_model1.getPath().equals(last_datamodel1.getPath())) {
                        ((DataModel) listData1.listData.get(m)).setColor("blue");
                    }
                }
                for (int n = 0; n < listData2.listData.size(); n++) {
                    data_model2 = (DataModel) listData2.listData.get(n);
                    if (data_model2.getPath().equals(last_datamodel2.getPath())) {
                        ((DataModel) listData2.listData.get(n)).setColor("red");
                    }
                }
            } else if (last_datamodel1.getTime().compareTo(last_datamodel2.getTime())
                    == 0) {
                for (int p = 0; p < listData1.listData.size(); p++) {
                    data_model1 = (DataModel) listData1.listData.get(p);
                    if (data_model1.getPath().equals(last_datamodel1.getPath())) {
                        ((DataModel) listData1.listData.get(p)).setColor("red");
                    }
                }
                for (int q = 0; q < listData2.listData.size(); q++) {
                    data_model2 = (DataModel) listData2.listData.get(q);
                    if (data_model2.getPath().equals(last_datamodel2.getPath())) {
                        ((DataModel) listData2.listData.get(q)).setColor("red");
                    }
                }
            }
//
            if (i < listData1.listData.size() && i < listData2.listData.size()) {
                data_model1 = (DataModel) listData1.listData.get(i);
                data_model2 = (DataModel) listData2.listData.get(i);
                result_Display = new ResultDisplay(data_model1.getPath(), data_model1.getTime(), data_model1.getColor(), data_model2.getPath(), data_model2.getTime(), data_model2.getColor());
            } else if (i >= listData1.listData.size()) {
                data_model2 = (DataModel) listData2.listData.get(i);
                result_Display = new ResultDisplay("", null, "white", data_model2.getPath(), data_model2.getTime(), data_model2.getColor());
            } else if (i >= listData2.listData.size()) {
                data_model1 = (DataModel) listData1.listData.get(i);
                result_Display = new ResultDisplay(data_model1.getPath(), data_model1.getTime(), data_model1.getColor(), "", null, "white");
            }

            // ADD
            list_result.add(result_Display);

        }
        return list_result;
    }
//==========

//
    public int compare_BlackBoard(Blackboard b) {

        int result = 0;

        int compareKS1 = listKS.get(0).compareAbstract(b);
        int compareKS2 = listKS.get(1).compareAbstract(b);
        if (compareKS1 == 1) {
            result = 11;
        } else if (compareKS1 == 2) {
            result = 12;
        } else {
            if (compareKS2 == 1) {
                result = 21;
            } else if (compareKS2 == 2) {
                result = 22;
            }
        }
        return result;
    }

}
