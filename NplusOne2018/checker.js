const Checker = function (count) {
    $.ajax("/_test.result.check", {
        type: "POST",
        data: {
            t: "tinkoff14476",
            answers: {0: "42", 1: "235000", 2: "6", 3: "6", 4: count, 5: "432", 6: "1", 7: "81", 8: "377"},
            _token: $('input[name="_token"]').val()
        },
        dataType: "JSON",
        success: function (t) {
            if (t && t.response) {
                const n = t.response.result.result;
                if (n === 9) {
                    console.log("Gooooooooooooooooooooooooooood", count);
                } else {
                    if (count % 10 === 0) {
                        console.log("Wrong ", count, n);
                    }
                }
            }
        }
    });
};

Checker(1000);
GlobalCount = 0;

setInterval(function () {
    Checker(GlobalCount);
    ++GlobalCount;
}, 1000);