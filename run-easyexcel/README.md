# 阿里巴巴开源的EasyExcel组件示例

## 概念

1. 工作簿：整个excel文件，是多个sheet的集合
2. 工作表：excel中的一个sheet对象
3. 表格：sheet中的一个table对象

## 示例

- 写Excel
    - [WriteSample](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteSample.java)
      - writeAutoWriter：根据头对象和列表向一个工作表中写一个表格
      - writeManualWither：根据头对象和列表向多个工作表中分别写一个表格
    - [WriteExcludeOrInclude](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteExcludeOrInclude.java)
      - writeExcludeColumn：排除指定列
      - writeIncludeColumn：导出指定列
    - [WriteComplexHead](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteComplexHead.java)
      - writeNoAnnotation：没有ExcelProperty注解的表头，会直接使用类字段作为表头
      - writeWithIndex：定义ExcelProperty的index属性，指定表头字段位置
      - writeWithMultiHead：在ExcelProperty定义多个表头，相邻的相同表头会合并
    - [WriteRepeatable](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteRepeatable.java)
      - writeOneSheet：向同一个excel同一个sheet中多次写入
      - writeDiffSheetWithSameHead：向同一个excel不同sheet中多次写入（表头相同）
      - writeDiffSheetWithDiffHead：向同一个excel不同sheet中多次写入（表头不同）
    - [WriteFormat](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteFormat.java)
      - writeFormatContent：使用注解实现数据内容格式化，改的是内容
      - writeFormatCell：使用注解定义表格列宽和行高
      - writeFormatStyleCell：使用注解修改表格样式，包括表头背景、表头字体、表体背景、表体字体等
    - [WriteComplexFormatCell](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteComplexFormatCell.java)
      - writeComplexFormatCell：超链接、备注、公式、指定单个单元格的样式、单个单元格多种样式
    - [WriteUseWriteHandler](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteUseWriteHandler.java)
      - writeByCellStyleStrategy：使用已有策略实现自定义样式，包括背景、字体等
      - writeByCustomCellStyleStrategyOfEasyexcel：「不推荐」使用easyexcel的方式自定义格式策略
      - writeByCustomCellStyleStrategyOfPoi：「不推荐」使用poi的方式自定义格式策略
      - writeUseLongestMatchColumnWidthStyleStrategy：使用自动列宽策略
      - writeUseCustomWriteHandler：使用自定义拦截器实现表单的个性化配置，包括单元格格式、列校验规则等
      - writeUseCustomWriteHandler2：实现插入批注（inMemory 需要设置为 true）
    - [WriteMergeCell](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteMergeCell.java)
      - writeMergeCellUseAnnotation：使用注解实现合并单元格
      - writeMergeCellCustom：使用合并策略实现合并单元格
    - [WriteTables](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteTables.java)
      - writeTable：同一表单中创建表格
      - writeTables：同一表单中创建不同表格（相同表头）
      - writeTablesWithDiffHead：同一表单中创建不同表格（不同表头）
    - [WriteByTemplate](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteByTemplate.java)
      - writeByTemplate：向模板文件中写入，会保留模板文件原内容，从原有内容下第一个空行开始写
    - [WriteDynamicData](./src/main/java/cn/howardliu/tutorials/easyexcel/write/WriteDynamicData.java)
      - writeDynamicHead：动态表头，可以实现多个表头
      - writeDynamicMultiHead：注解表头+动态表头，以动态表头为准。可以在注解表头类中实现格式定义，然后通过动态表头实现国际化。
      - writeDynamicData：动态表体
- 填充Excel
    - [FillSample](./src/main/java/cn/howardliu/tutorials/easyexcel/fill/FillSample.java)
      - fillUseObject：填充对象
      - fillUseMap：填充map结构对象
    - [FillList](./src/main/java/cn/howardliu/tutorials/easyexcel/fill/FillList.java)
      - fillListInMemory：一次写入列表，全部内存操作
      - fillListSegment：多次填充，会使用文件缓存，适合数据量比较大时，分页填充模板
    - [FillComplexData](./src/main/java/cn/howardliu/tutorials/easyexcel/fill/FillComplexData.java)
      - fillObjectAndListInMemory：填充对象+列表
      - fillObjectAndListManual：大数量的时候手动填充对象+列表
      - fillObjectAndListHorizontal：横向填充数据
      - fillMultiList：填充多个表格

![公众号：「看山的小屋」](http://static.howardliu.cn/about/kanshanshuo.png)
