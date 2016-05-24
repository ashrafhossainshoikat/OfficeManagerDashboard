package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import be.objectify.deadbolt.java.actions.Dynamic;
import connectplus.services.deadbolt.DeadboltHandler;
import models.task.TaskCategory;
import services.AppConstants;
import services.JavaUtils;
import views.html.taskCategory.*;


public class CategoryManagement extends Controller {

	static Form<TaskCategory> taskCategoryForm = Form.form(TaskCategory.class);

	/* show task_Category form */
	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_CREATE, handler = DeadboltHandler.class)
	public static Result create() {

		return ok(create.render(taskCategoryForm));
	}

	/* create & store a task_Category from view */
	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_CREATE, handler = DeadboltHandler.class)
    public static Result insert() {
		Form<TaskCategory> filledForm = taskCategoryForm.bindFromRequest();

		if (filledForm.hasErrors()) {
			return badRequest(create.render(filledForm));
		} else {
			TaskCategory taskCategory = filledForm.get();
			taskCategory.createdBy = JavaUtils.getCurrentUsername();
			taskCategory.createDate = new Date();
			taskCategory.isActive = false;
			taskCategory.isApproved = false;
			
			taskCategory.create();
			flash("msg", "A new Task_Category is created Successfully..");
			return redirect(controllers.routes.CategoryManagement.list());
		}
	}

	/* show the list of task_Category */
	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_VIEW, handler = DeadboltHandler.class)
	public static Result list() {
		List<TaskCategory> listOfTaskCategory = TaskCategory.all();
		return ok(list.render(listOfTaskCategory));
	}

	/* show each task_Category details */
	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_VIEW, handler = DeadboltHandler.class)
	public static Result show(Integer id) {
		TaskCategory taskCategory = TaskCategory.get(id);
		return ok(show.render(taskCategory));
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_UPDATE, handler = DeadboltHandler.class)
    public static Result edit(Integer id) {
		TaskCategory taskCategory = TaskCategory.get(id);
		return ok(edit.render(taskCategoryForm.fill(taskCategory)));
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_UPDATE, handler = DeadboltHandler.class)
	public static Result update() {
		Form<TaskCategory> filledForm = taskCategoryForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(edit.render(filledForm));
		}

		else {
			TaskCategory tc = filledForm.get();
			TaskCategory taskCategory = TaskCategory.get(tc.id);
			taskCategory.categoryName = tc.categoryName;
			taskCategory.lastUpdatedBy = JavaUtils.getCurrentUsername();
			taskCategory.lastUpdateDate = new Date();
			taskCategory.isActive = false;
			taskCategory.isApproved = false;

			taskCategory.update();
			flash("msg", "Task Category " + taskCategory.categoryName + " has been updated");
			return ok(show.render(taskCategory));
		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_APPROVE, handler = DeadboltHandler.class)
	public static Result showCreateApprovalForm() {
		List<TaskCategory> listOfTaskCategory = TaskCategory.allUnapproved();
		return ok(approve.render(listOfTaskCategory));
	}

	@Dynamic(value =  AppConstants.ACTION_DEADBOLT_NAME_CATEGORY_APPROVE, handler = DeadboltHandler.class)
	public static Result approveCreate() {
		final Map<String, String[]> data = request().body().asFormUrlEncoded();
		String taskCategoryIdStr = data.get(AppConstants.FORM_FIELD_NAME_CATEGORY_ID)[0];

		int taskCategoryId = 0;
		if (taskCategoryIdStr != null && !taskCategoryIdStr.isEmpty())
			taskCategoryId = Integer.parseInt(taskCategoryIdStr);

		TaskCategory.approveById(taskCategoryId);

		//return redirect(routes.CategoryManagement.list());
		return redirect(routes.CategoryManagement.showCreateApprovalForm());
	}

}
