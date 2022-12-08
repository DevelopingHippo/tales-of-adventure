FROM php:8.0-apache

RUN a2enmod ssl && a2enmod rewrite
RUN mkdir -p /etc/apache2/ssl

RUN docker-php-ext-install mysqli && docker-php-ext-enable mysqli
RUN apt-get update && apt-get upgrade -y

COPY config/certs/toa.key /etc/apache2/ssl/toa.key
COPY config/certs/toa.pem /etc/apache2/ssl/toa.pem
COPY config/toa.conf /etc/apache2/sites-available/000-default.conf