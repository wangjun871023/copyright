package com.copyright.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.copyright.common.hibernate.HibernateDao;
import com.copyright.dao.StudentDao;
import com.copyright.dao.TempDao;
import com.copyright.pojo.StudentEntity;
import com.copyright.pojo.TempEntity;
import com.copyright.util.StringUtils;

@Repository
public class TempDaoImpl extends HibernateDao<TempEntity, String> implements TempDao{
	@Autowired
	private StudentDao studentDao;
	
	
	/**
	 * 写入临时表
	 */
	@Override
	public void createIntoTempTable(File courseHeadExcel,String uploadFileFileName,String fileId) throws Exception{
		  FileInputStream fi = new FileInputStream(courseHeadExcel);    
		  Workbook wb = null;  
		  if (uploadFileFileName.toLowerCase().endsWith("xls")) {   
              wb = new HSSFWorkbook(fi);  
          }else if(uploadFileFileName.toLowerCase().endsWith("xlsx")) {   
              wb = new XSSFWorkbook(fi);  
          }   
		  Sheet sheet = wb.getSheetAt(0);    
		  int rowNum = sheet.getLastRowNum()+1;    
          //i 从1开始表示第一行为标题 不包含在数据中  
          for(int i=1;i<rowNum;i++){  
              Row row = sheet.getRow(i);    
              int cellNum = row.getLastCellNum();    
              TempEntity tempEntity = new TempEntity();
              for(int j=0;j<cellNum;j++){    
                  Cell cell = row.getCell(j);    
                  String cellValue = null;    
                  switch(cell.getCellType()){ 
                  	  //判断excel单元格内容的格式，并对其进行转换，以便插入数据库    
                      case 0 : cellValue = String.valueOf((int)cell.getNumericCellValue()); break;    
                      case 1 : cellValue = cell.getStringCellValue(); break;    
                      case 2 : cellValue = String.valueOf(cell.getDateCellValue()); break;    
                      case 3 : cellValue = ""; break;    
                      case 4 : cellValue = String.valueOf(cell.getBooleanCellValue()); break;    
                      case 5 : cellValue = String.valueOf(cell.getErrorCellValue()); break;    
                  }
                  Field field = tempEntity.getClass().getDeclaredField("column"+(j+1));
                  field.setAccessible(true);
                  field.set(tempEntity, cellValue);
              }   
              tempEntity.setCreateTime(System.currentTimeMillis()+"");
              tempEntity.setFileId(fileId);
              tempEntity.setRowNum(i+"");
              save(tempEntity);
          }   
	}

	@Override
	public void checkTempTable(String fileId,String courseId,String classHeadId) {
		String hql = "from TempEntity entity where fileId = ?";
		List <TempEntity> list = find(hql,fileId);
		if (list!=null&&list.size()>0){
			List<StudentEntity> studentsAll = studentDao.findByCourseId(courseId);
			if (!StringUtils.isEmpty(classHeadId)){
				List<StudentEntity> studentsClassHead = studentDao.findByClassHeadId(classHeadId);
				if (studentsClassHead!=null&&studentsClassHead.size()>0){
					for (StudentEntity studentEntity : studentsClassHead) {
						studentEntity.setClassHeadId("");
						studentDao.saveStudent(studentEntity);
					}
				}
			}
			Map<String,StudentEntity> studentsMap = null;
			if (studentsAll!=null && studentsAll.size()>0){
				studentsMap = new HashMap<String, StudentEntity>();
				for (StudentEntity studentEntity : studentsAll) {
					studentsMap.put(studentEntity.getStudentNo(), studentEntity);

				}
			}
			if (studentsMap!=null){
				for (TempEntity tempEntity : list) {
					//对应学号
					String studentNo = tempEntity.getColumn1();
					if (studentsMap.containsKey(studentNo)){
						//已经存在
						delete(tempEntity);
						StudentEntity studentEntity = studentsMap.get(studentNo);
						studentEntity.setClassHeadId(classHeadId);
						studentDao.saveStudent(studentEntity);
						studentsMap.remove(studentNo);
					}
				}
			}
		}
	}
}
