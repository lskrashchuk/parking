CREATE TABLE "user" (
	"id" serial NOT NULL,
	"first_name" character varying(100) NOT NULL,
	"last_name" character varying(100) NOT NULL,
	"photo" bytea,
	"phone" character varying(100) NOT NULL,
	"email" character varying(256) NOT NULL,
	"password" character(32) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"user_type_id" int NOT NULL,
	"role" int NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car" (
	"id" serial NOT NULL,
	"reg_number" character varying(50) NOT NULL,
	"make_id" int NOT NULL,
	"model_id" int NOT NULL,
	"car_type_id" int NOT NULL,
	"year_produced" int,
	"color_id" int,
	CONSTRAINT car_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "section" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	"description" character varying(300),
	"plan" bytea,
	CONSTRAINT section_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "registry" (
	"id" serial NOT NULL,
	"car_id" int NOT NULL,
	"arrived" TIMESTAMP NOT NULL,
	"departed" TIMESTAMP NOT NULL,
	"place_id" int NOT NULL,
	CONSTRAINT registry_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "color" (
	"id" serial NOT NULL,
	"color" character varying(100) NOT NULL,
	CONSTRAINT color_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "model" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	"make_id" int NOT NULL,
	CONSTRAINT model_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car_type" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	"place_size_id" int NOT NULL,
	CONSTRAINT car_type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "make" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	CONSTRAINT make_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "place" (
	"id" serial NOT NULL,
	"section_id" int NOT NULL,
	"number" int NOT NULL,
	"place_size_id" int NOT NULL,
	CONSTRAINT place_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car_photo" (
	"id" serial NOT NULL,
	"car_id" int NOT NULL,
	"photo" bytea NOT NULL,
	CONSTRAINT car_photo_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "place_size" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"length" numeric(5,1) NOT NULL,
	"width" numeric(5,1) NOT NULL,
	CONSTRAINT place_size_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_type_2_section" (
	"user_type_id" int NOT NULL,
	"section_id" int NOT NULL,
	CONSTRAINT user_type_2_section_pk PRIMARY KEY ("user_type_id","section_id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_type" (
	"id" serial NOT NULL,
	"name" character varying(100) NOT NULL,
	CONSTRAINT user_type_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "binding" (
	"id" serial NOT NULL,
	"place_id" int NOT NULL,
	"car_id" int NOT NULL,
	"binded" TIMESTAMP NOT NULL,
	"released" TIMESTAMP NOT NULL,
	CONSTRAINT binding_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_2_car" (
	"user_id" int NOT NULL,
	"car_id" int NOT NULL,
	CONSTRAINT user_2_car_pk PRIMARY KEY ("user_id","car_id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user" ADD CONSTRAINT "user_fk0" FOREIGN KEY ("user_type_id") REFERENCES "user_type"("id");

ALTER TABLE "car" ADD CONSTRAINT "car_fk0" FOREIGN KEY ("make_id") REFERENCES "make"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk1" FOREIGN KEY ("model_id") REFERENCES "model"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk2" FOREIGN KEY ("car_type_id") REFERENCES "car_type"("id");
ALTER TABLE "car" ADD CONSTRAINT "car_fk3" FOREIGN KEY ("color_id") REFERENCES "color"("id");


ALTER TABLE "registry" ADD CONSTRAINT "registry_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "registry" ADD CONSTRAINT "registry_fk1" FOREIGN KEY ("place_id") REFERENCES "place"("id");


ALTER TABLE "model" ADD CONSTRAINT "model_fk0" FOREIGN KEY ("make_id") REFERENCES "make"("id");

ALTER TABLE "car_type" ADD CONSTRAINT "car_type_fk0" FOREIGN KEY ("place_size_id") REFERENCES "place_size"("id");


ALTER TABLE "place" ADD CONSTRAINT "place_fk0" FOREIGN KEY ("section_id") REFERENCES "section"("id");
ALTER TABLE "place" ADD CONSTRAINT "place_fk1" FOREIGN KEY ("place_size_id") REFERENCES "place_size"("id");

ALTER TABLE "car_photo" ADD CONSTRAINT "car_photo_fk0" FOREIGN KEY ("car_id") REFERENCES "car"("id");


ALTER TABLE "user_type_2_section" ADD CONSTRAINT "user_type_2_section_fk0" FOREIGN KEY ("user_type_id") REFERENCES "user_type"("id");
ALTER TABLE "user_type_2_section" ADD CONSTRAINT "user_type_2_section_fk1" FOREIGN KEY ("section_id") REFERENCES "section"("id");


ALTER TABLE "binding" ADD CONSTRAINT "binding_fk0" FOREIGN KEY ("place_id") REFERENCES "place"("id");
ALTER TABLE "binding" ADD CONSTRAINT "binding_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");

ALTER TABLE "user_2_car" ADD CONSTRAINT "user_2_car_fk0" FOREIGN KEY ("user_id") REFERENCES "user"("id");
ALTER TABLE "user_2_car" ADD CONSTRAINT "user_2_car_fk1" FOREIGN KEY ("car_id") REFERENCES "car"("id");

