create table bookreturn
(
    Id        varchar(20) not null comment '编号',
    BookName  varchar(20) null comment '书名',
    Restudent varchar(20) null comment '借阅的学生',
    Reclass   varchar(20) null comment '学生的班级',
    Restuid   varchar(20) null comment '学号',
    day       varchar(20) null comment '借阅天数'
);

create table books
(
    Id       int(60)     not null comment '编号',
    BookName varchar(20) not null comment '书名',
    Number   int(6)      null comment '书的数量',
    constraint books_BookName_uindex
        unique (BookName),
    constraint books_Id_uindex
        unique (Id)
)
    comment '书籍表格';

alter table books
    add primary key (Id);

create table imagebook
(
    Id      int(20)      not null,
    Name    varchar(20)  null,
    pic     mediumblob   not null,
    comment varchar(500) not null,
    constraint imageBook_Id_uindex
        unique (Id),
    constraint imagebook_Name_uindex
        unique (Name)
);

alter table imagebook
    add primary key (Id);

create table login
(
    userId   varchar(255) not null
        primary key,
    password varchar(255) null,
    position varchar(255) null
);

create table tb_brand
(
    id           int auto_increment
        primary key,
    brand_name   varchar(20)  null,
    company_name varchar(20)  null,
    ordered      int          null,
    description  varchar(100) null,
    status       int          null
);

create table usertable
(
    id       int auto_increment
        primary key,
    username varchar(32) not null,
    password varchar(32) not null,
    constraint userTable_username_uindex
        unique (username)
);

create table xsqk
(
    stuId     varchar(30)    not null
        primary key,
    stuName   varchar(30)    null,
    stuSex    varchar(30)    null,
    stuAge    int            null,
    stuJg     varchar(30)    null,
    stuZy     varchar(30)    null,
    stuSourse decimal(10, 2) null
);


