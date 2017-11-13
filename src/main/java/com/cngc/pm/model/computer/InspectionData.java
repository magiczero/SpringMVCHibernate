package com.cngc.pm.model.computer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cmp_inspection_data")
public class InspectionData {

	private Long id;
	private Long computerTaskId;
	private String itemName;
	private String data;
	private boolean compliance;
	private String mark;
	private boolean auto;
	private String judgeByUser;
	private boolean gather;		//是否为汇总数据
	private int	sum;			//违规记录数
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="computer_task_id")
	public Long getComputerTaskId() {
		return computerTaskId;
	}
	public void setComputerTaskId(Long computerTaskId) {
		this.computerTaskId = computerTaskId;
	}
	@Column(name="item_name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="data_")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Column(name="compliance")
	public boolean isCompliance() {
		return compliance;
	}
	public void setCompliance(boolean compliance) {
		this.compliance = compliance;
	}
	@Column(name="mark")
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	@Column(name="auto")
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	@Column(name="judge_by_user")
	public String getJudgeByUser() {
		return judgeByUser;
	}
	public void setJudgeByUser(String judgeByUser) {
		this.judgeByUser = judgeByUser;
	}
	@Column(name="gather")
	public boolean isGather() {
		return gather;
	}
	public void setGather(boolean gather) {
		this.gather = gather;
	}
	@Column(name="_sum")
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	
}
