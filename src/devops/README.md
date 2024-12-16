## Создание доменного имени на локальном DNS-сервере macos

1. В файл `/etc/hosts` добавляем под sudo

```
127.0.0.1 example.local
```

2. Затем очистить кеш DNS

```
sudo dscacheutil -flushcache; sudo killall -HUP mDNSResponder
```

3. Проверка работоспособности 

```
ping example.local
```

## Создание самоподписанного сертификата

1. Генерация закрытого ключа

```
openssl genrsa -out private.key 2048
```

2. Создание запроса на подпись сертификата (CSR)

```
openssl req -new -key private.key -out cert.csr
```

При создании необходимо будет заполнить следующую информацию:

Country Name (2 letter code): `RU`
State or Province Name: `Moscow`
Locality Name: `Moscow`
Organization Name: `Example Inc`
Organizational Unit Name: `IT Department`
Common Name: `example.local`
Email Address: `admin@example.local`

3. Создание самоподписанного сертификата

```
openssl x509 -req -days 365 -in cert.csr -signkey private.key -out public.crt
```

4. Объединение закрытого ключа и сертификата

```
cat private.key public.crt > example.pem
```