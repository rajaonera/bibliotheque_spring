create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id),
    unique (name)
);

create table users
(
    id         bigint    default nextval('users_id_seq'::regclass) not null,
    name       varchar(255)                                        not null,
    email      varchar(255)                                        not null,
    password   varchar(255)                                        not null,
    profile    varchar(255)                                        not null,
    role_id    integer                                             not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    active     boolean                                             not null,
    primary key (id),
    unique (email),
    foreign key (role_id) references roles,
    constraint users_profile_check
        check ((profile)::text = ANY
            (ARRAY [('ETUDIANT'::character varying)::text, ('PROFESSEUR'::character varying)::text, ('PROFESSIONNEL'::character varying)::text, ('ANONYME'::character varying)::text, ('BIBLIOTHECAIRE'::character varying)::text]))
    );

create table penalties
(
    id         bigint default nextval('penalties_id_seq'::regclass) not null,
    user_id    bigint                                               not null,
    start_date date                                                 not null,
    end_date   date                                                 not null,
    days       integer                                              not null,
    reason     varchar(255),
    active     boolean                                              not null,
    primary key (id),
    foreign key (user_id) references users
);

create table holidays
(
    id           bigint default nextval('holidays_id_seq'::regclass) not null,
    holiday_date date                                                not null,
    name         varchar(255)                                        not null,
    date         date,
    primary key (id),
    unique (holiday_date)
);

create table activity_logs
(
    id          bigint    default nextval('activity_logs_id_seq'::regclass) not null,
    user_id     bigint,
    action_type varchar(255)                                                not null,
    description varchar(255),
    created_at  timestamp default CURRENT_TIMESTAMP,
    timestamp   timestamp(6),
    primary key (id),
    foreign key (user_id) references users,
    constraint activity_logs_action_type_check
        check ((action_type)::text = ANY
            (ARRAY [('EMPRUNT'::character varying)::text, ('RETOUR'::character varying)::text, ('RESERVATION'::character varying)::text, ('PROLONGATION'::character varying)::text, ('PENALITE'::character varying)::text, ('CREATION_LIVRE'::character varying)::text, ('SUPPRESSION_LIVRE'::character varying)::text, ('CONNEXION'::character varying)::text, ('MODIFICATION_UTILISATEUR'::character varying)::text]))
    );

create table loan_policies
(
    id                        bigint default nextval('loan_policies_id_seq'::regclass) not null,
    user_profil               varchar(255)                                             not null,
    loan_type                 varchar(255)                                             not null,
    max_loans                 integer                                                  not null,
    loan_duration_days        integer                                                  not null,
    max_prolongations         integer                                                  not null,
    allow_reservation         boolean                                                  not null,
    allow_prolongation        boolean                                                  not null,
    penalty_days_per_late_day integer                                                  not null,
    primary key (id),
    constraint uq_user_role_type
        unique (user_profil, loan_type)
);

create table book_categories
(
    id   bigint default nextval('book_categories_id_seq'::regclass) not null,
    name varchar(255)                                               not null,
    primary key (id),
    unique (name)
);

create table languages
(
    id   bigint default nextval('languages_id_seq'::regclass) not null,
    name varchar(255)                                         not null,
    primary key (id),
    unique (name)
);

create table books
(
    id          bigint default nextval('books_id_seq'::regclass) not null,
    title       varchar(255)                                     not null,
    author      varchar(255),
    isbn        varchar(255),
    category_id bigint,
    language_id bigint,
    primary key (id),
    unique (isbn),
    foreign key (category_id) references book_categories,
    foreign key (language_id) references languages
);

create table book_copies
(
    id      bigint default nextval('book_copies_id_seq'::regclass) not null,
    book_id bigint                                                 not null,
    status  varchar(255)                                           not null,
    code    varchar(255),
    primary key (id),
    foreign key (book_id) references books
        on delete cascade,
    constraint book_copies_status_check
        check ((status)::text = ANY
            (ARRAY [('DISPONIBLE'::character varying)::text, ('EMPRUNTE'::character varying)::text, ('RESERVE'::character varying)::text]))
    );

create table loans
(
    id           bigint  default nextval('loans_id_seq'::regclass) not null,
    user_id      bigint                                            not null,
    book_copy_id bigint                                            not null,
    start_date   date                                              not null,
    due_date     date                                              not null,
    return_date  date,
    extended     boolean default false,
    loan_type    varchar(255)                                      not null,
    returned     boolean,
    primary key (id),
    constraint unique_loan
        unique (book_copy_id, return_date),
    foreign key (book_copy_id) references book_copies,
    foreign key (user_id) references users
);

create table reservations
(
    id               bigint      default nextval('reservations_id_seq'::regclass) not null,
    user_id          bigint                                                       not null,
    book_id          bigint                                                       not null,
    created_at       timestamp   default CURRENT_TIMESTAMP,
    status           varchar(20) default 'EN_ATTENTE'::character varying          not null,
    available_from   date,
    notified         boolean,
    reservation_date date,
    active           boolean                                                      not null,
    primary key (id),
    foreign key (book_id) references books,
    foreign key (user_id) references users,
    constraint reservations_status_check
        check ((status)::text = ANY
            ((ARRAY ['EN_ATTENTE'::character varying, 'DISPONIBLE'::character varying, 'EXPIREE'::character varying])::text[]))
    );

