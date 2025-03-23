CREATE TABLE news (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    cover_url VARCHAR(255),
    published_at DATE NOT NULL
);

INSERT INTO news (title, content, cover_url, published_at) VALUES
    (
        'Мастер-класс «Создание мобильных приложений на Swift»',
        'Приглашаем на практический мастер-класс по разработке iOS-приложений. Участники создадут прототип приложения с нуля и получат фидбек от менторов AppStore. Регистрация до конца недели!',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlie1992hmZThvH7uxj55gLKrXAfxf5LFCXg&s',
        '2025-03-15'
    ),
    (
        'Защита выпускных проектов по направлению «Веб-разработка»',
        '23 проекта были представлены перед комиссией из представителей Яндекс и Tinkoff. Лучшие работы получат гранты на реализацию. Полные записи защит доступны в личном кабинете.',
        'https://gb.ru/blog/wp-content/uploads/2022/01/%D0%A1%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5-%D1%8F%D0%B7%D1%8B%D0%BA%D0%B0-%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F1.jpg',
        '2024-11-05'
    ),
    (
        'Соглашение о партнерстве с облачной платформой Selectel',
        'Теперь студенты получают бесплатный доступ к облачным серверам для учебных проектов. Первые 500 участников смогут развернуть свой кластер Kubernetes уже на этой неделе!',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPCn_k6i7UCrOK-yhCzaZWgO3ACxy-0vjENw&s',
        current_date - interval '2 days'
    ),
    (
        'Цикл лекций «Big Data в реальном времени»',
        'Стартует новая серия мероприятий с архитекторами данных из Ozon и Wildberries. Практические кейсы по обработке потоков данных и оптимизации кластеров.',
        'https://lemon.school/storage/2024/02/IT-i-biznes_-YAk-tehnologiyi-vplyvayut-na-rozvytok-suchasnyh-pidpryyemstv.jpg',
        '2025-04-01'
    ),
    (
        'Международный конкурс по алгоритмическому программированию',
        'Победители получат путевки на финал в Сингапуре и денежные призы. Для подготовки открыт специальный тренажер с задачами прошлых лет. Регистрация до 15 сентября.',
        null,
        '2024-08-20'
    ),
    (
        'Новый онлайн-курс «Основы DevOps: CI/CD на практике»',
        'Авторы курса — инженеры с опытом внедрения в Альфа-Банке и МТС. Студенты будут работать с Docker, GitLab и мониторингом Prometheus. Первые 2 модуля доступны бесплатно.',
        null,
        current_date + interval '3 days'
    );
