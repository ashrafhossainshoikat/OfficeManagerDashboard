package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;


@Entity
public abstract class BaseModelInt extends BaseModel {

	@Id
	public Integer id;
}