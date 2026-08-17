create table service_requests (
    id uuid primary key,
    protocol varchar(32) not null unique,
    requester_name varchar(120) not null,
    requester_document varchar(32) not null,
    description text not null,
    status varchar(32) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null
);